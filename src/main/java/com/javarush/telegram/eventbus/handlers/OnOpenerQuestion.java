package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.events.OpenerQuestionEvent;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

@Immutable
public final class OnOpenerQuestion implements EventHandler<OpenerQuestionEvent> {

    private static final String PLEASE_WAIT = "Please wait!";
    private static final String OPENER = "opener";

    @Override
    @Subscribe
    public void handle(OpenerQuestionEvent event) {
        var survey = event.context().survey();
        var question = survey.questions().nextQuestion();
        var responder = event.responder();

        event.previousAnswer()
                .ifPresent(answer -> question.accept(survey, answer));

        var maybeQuestionValue = question.value();
        maybeQuestionValue.ifPresentOrElse(
                questionValue -> responder.execute(new TextMessage(questionValue)),
                () -> {
                    var message = responder.execute(new TextMessage(PLEASE_WAIT));

                    var prompt = TelegramBotFileUtil.loadPrompt(OPENER);
                    var userInfo = survey.newUserInfo();
                    var gptAnswer = event.context().chatGPTService().sendMessage(prompt, userInfo.toString());

                    responder.execute(new UpdatedTextMessage(message, gptAnswer));
                }
        );
    }
}
