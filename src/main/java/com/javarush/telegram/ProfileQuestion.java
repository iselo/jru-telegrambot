package com.javarush.telegram;

import com.javarush.telegram.questions.*;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Immutable
public final class ProfileQuestion extends AbstractMessage {

    private final IUserInfoBuilder userInfoBuilder = UserInfo.newBuilder();

    private final List<Question> questions = new ArrayList<>();

    public ProfileQuestion(TelegramBotContext context) {
        super(context);
        configure();
    }

    private void configure() {
        questions.addAll(
                List.of(
                        new FirstQuestion(Optional.of("Name")),
                        new SexQuestion(Optional.of("Sex")),
                        new AgeQuestion(Optional.of("Age")),
                        new LastQuestion(Optional.empty())
                )
        );
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {

        if (context().getMode() == DialogMode.PROFILE && !questions.isEmpty()) {
            Question question = questions.remove(0);
            Optional<String> maybeLastQuestion = question.value();

            String messageText = update.getMessage().getText();
            question.accept(userInfoBuilder, messageText);

            Responder responder = new Responder(bot, getChatId(update));

            maybeLastQuestion.ifPresentOrElse(
                    q -> responder.execute(new TextMessage(q)),
                    () -> {
                        Message message = responder.execute(new TextMessage("Please wait."));

                        String prompt = TelegramBotFileUtil.loadPrompt("profile");
                        UserInfo userInfo = userInfoBuilder.build();
                        String answer = context().chatGPTService().sendMessage(prompt, userInfo.toString());

                        responder.execute(new UpdatedTextMessage(message, answer));
                    }
            );

            return true;
        }

        return false;
    }
}
