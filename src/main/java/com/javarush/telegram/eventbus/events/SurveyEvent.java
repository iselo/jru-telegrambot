package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.EventWithReturn;
import com.javarush.telegram.survey.UserInfoSurvey;

import java.util.function.Consumer;

@Immutable
public final class SurveyEvent extends EventWithReturn<String, UserInfoSurvey> {

    public SurveyEvent(String payload, Consumer<UserInfoSurvey> consumer) {
        super(payload, consumer);
    }
}
