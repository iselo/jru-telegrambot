package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.AskQuestionEvent;
import com.javarush.telegram.eventbus.events.LastQuestionEvent;
import com.javarush.telegram.eventbus.events.OpenerQuestionEvent;
import com.javarush.telegram.eventbus.events.SurveyEvent;

@Immutable
public final class OnOpenerQuestion implements EventHandler<OpenerQuestionEvent>, Subscribable {

    private static final String OPENER = "opener";

    @Override
    @Subscribe
    public void handle(OpenerQuestionEvent event) {
        var previousAnswer = event.payload().value();

        new SurveyEvent(
                Payload.empty(),
                (survey) -> {
                    var question = survey.questions().nextQuestion();

                    previousAnswer
                            .ifPresent(answer -> question.accept(survey, answer));

                    var maybeQuestionValue = question.value();
                    maybeQuestionValue
                            .ifPresentOrElse(
                                    questionValue -> new AskQuestionEvent(Payload.of(questionValue)).post(),
                                    () -> new LastQuestionEvent(Payload.of(OPENER)).post()
                            );
                }
        ).post();
    }
}
