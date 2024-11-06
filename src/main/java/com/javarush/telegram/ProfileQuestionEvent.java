package com.javarush.telegram;

import com.javarush.telegram.profile.AbstractQuestionHandler;
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

            maybeLastQuestion.ifPresentOrElse(
                    q -> sendTextMessage(bot, update, q),
                    () -> {
                        UserInfo userInfo = context().userInfoBuilder().build();
                        String prompt = TelegramBotFileUtil.loadPrompt("profile");
                        String answer = context().chatGPTService().sendMessage(prompt, userInfo.toString());
                        sendTextMessage(bot, update, answer);
                    }
            );

            return true;
        }

        return false;
    }
}
