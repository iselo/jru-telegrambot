package com.javarush.telegram;

import com.javarush.telegram.command.SendTextMessage;
import com.javarush.telegram.command.UpdateTextMessage;
import com.javarush.telegram.profile.AbstractQuestionHandler;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;
import java.util.List;
import java.util.Optional;

@Immutable
public final class ProfileQuestionEvent extends AbstractMessage {

    public ProfileQuestionEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        List<AbstractQuestionHandler> questionHandlers = context().questionHandlers();

        if (context().getMode() == DialogMode.PROFILE && !questionHandlers.isEmpty()) {
            AbstractQuestionHandler questionHandler = questionHandlers.remove(0);
            Optional<String> maybeLastQuestion = questionHandler.apply(messageText);

            Long chatId = getChatId(update);

            maybeLastQuestion.ifPresentOrElse(
                    q -> new SendTextMessage(q).handle(bot, chatId),
                    () -> {
                        UserInfo userInfo = context().userInfoBuilder().build();
                        String prompt = TelegramBotFileUtil.loadPrompt("profile");
                        Message message = new SendTextMessage("Please wait.").handle(bot, chatId);
                        String answer = context().chatGPTService().sendMessage(prompt, userInfo.toString());
                        new UpdateTextMessage(message, answer).handle(bot, chatId);
                    }
            );

            return true;
        }

        return false;
    }
}
