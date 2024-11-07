package com.javarush.telegram;

import com.javarush.telegram.questions.AbstractQuestionHandler;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;
import java.util.List;
import java.util.Optional;

@Immutable
public final class ProfileQuestion extends AbstractMessage {

    public ProfileQuestion(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        List<AbstractQuestionHandler> questionHandlers = context().questionHandlers();

        if (context().getMode() == DialogMode.PROFILE && !questionHandlers.isEmpty()) {
            AbstractQuestionHandler questionHandler = questionHandlers.remove(0);
            Optional<String> maybeLastQuestion = questionHandler.apply(messageText);

            Responder responder = new Responder(bot, getChatId(update));

            maybeLastQuestion.ifPresentOrElse(
                    q -> responder.accept(new TextMessage(q)),
                    () -> {
                        Message message = responder.accept(new TextMessage("Please wait."));

                        String prompt = TelegramBotFileUtil.loadPrompt("profile");
                        UserInfo userInfo = context().userInfoBuilder().build();
                        String answer = context().chatGPTService().sendMessage(prompt, userInfo.toString());

                        responder.accept(new UpdatedTextMessage(message, answer));
                    }
            );

            return true;
        }

        return false;
    }
}
