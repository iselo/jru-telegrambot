package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.events.AskQuestionEvent;
import com.javarush.telegram.eventbus.events.LastQuestionEvent;
import com.javarush.telegram.eventbus.events.OpenerQuestionEvent;

@Immutable
public final class OnOpenerQuestion implements EventHandler<OpenerQuestionEvent> {

    private static final String OPENER = "opener";

    @Override
    @Subscribe
    public void handle(OpenerQuestionEvent event) {
        var responder = event.responder();
        var context = event.context();
        var previousAnswer = event.previousAnswer();
        var eventBus = context.eventBus();
        var survey = context.survey();
        var question = survey.questions().nextQuestion();

        previousAnswer
                .ifPresent(answer -> question.accept(survey, answer));

        var maybeQuestionValue = question.value();
        maybeQuestionValue.ifPresentOrElse(
                questionValue -> eventBus.post(new AskQuestionEvent(responder, context, questionValue)),
                () -> eventBus.post(new LastQuestionEvent(responder, context, OPENER))
        );
    }
}
