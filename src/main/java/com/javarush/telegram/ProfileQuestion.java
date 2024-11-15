package com.javarush.telegram;

import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;
import com.javarush.telegram.survey.AgeQuestion;
import com.javarush.telegram.survey.FirstNameQuestion;
import com.javarush.telegram.survey.GenderQuestion;
import com.javarush.telegram.survey.LastEmptyQuestion;
import com.javarush.telegram.survey.Question;
import com.javarush.telegram.survey.UserInfo;
import com.javarush.telegram.survey.UserInfoSurvey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.concurrent.Immutable;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public final class ProfileQuestion extends AbstractMessage {

    private static final int TOP_ELEMENT = 0;

    private final UserInfoSurvey survey = new UserInfoSurvey();

    private final List<Question> questions = new ArrayList<>();

    public ProfileQuestion(TelegramBotContext context) {
        super(context);
        configure();
    }

    private void configure() {
        questions.addAll(
                List.of(
                        new FirstNameQuestion(),
                        new GenderQuestion(),
                        new AgeQuestion(),
                        new LastEmptyQuestion()
                )
        );
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {

        if (context().getMode() == DialogMode.PROFILE && !questions.isEmpty()) {

            String previousQuestionAnswer = update.getMessage().getText();
            Question question = questions.remove(TOP_ELEMENT);
            question.accept(survey, previousQuestionAnswer);

            Responder responder = new Responder(bot, getChatId(update));

            Optional<String> maybeQuestion = question.value();
            maybeQuestion.ifPresentOrElse(
                    q -> responder.execute(new TextMessage(q)),
                    () -> {
                        Message message = responder.execute(new TextMessage("Please wait."));

                        String prompt = TelegramBotFileUtil.loadPrompt("profile");
                        UserInfo userInfo = survey.newUserInfo();
                        String answer = context().chatGPTService().sendMessage(prompt, userInfo.toString());

                        responder.execute(new UpdatedTextMessage(message, answer));
                    }
            );

            return true;
        }

        return false;
    }
}
