package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.AskQuestionEvent;
import com.javarush.telegram.eventbus.events.LastQuestionEvent;
import com.javarush.telegram.eventbus.events.ProfileQuestionEvent;
import com.javarush.telegram.eventbus.events.SurveyEvent;

@Immutable
public final class OnProfileQuestion implements EventHandler<ProfileQuestionEvent>, Subscribable {

    private static final String PROFILE = "profile";

    @Override
    @Subscribe
    public void handle(ProfileQuestionEvent event) {
        var previousAnswer = event.getPayload();
        new SurveyEvent((survey) -> {
            var question = survey.questions().nextQuestion();

            previousAnswer.ifPresent(answer -> question.accept(survey, answer));

            var maybeQuestionValue = question.value();
            maybeQuestionValue.ifPresentOrElse(
                    questionValue -> new AskQuestionEvent(questionValue).post(),
                    () -> new LastQuestionEvent(PROFILE).post()
            );
        }).post();
    }
}
