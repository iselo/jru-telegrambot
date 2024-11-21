package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.EventWithConsumer;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.survey.UserInfoSurvey;

import java.util.function.Consumer;

@Immutable
public final class SurveyEvent extends EventWithConsumer<Payload<String>, UserInfoSurvey> {

    public SurveyEvent(Payload<String> payload, Consumer<UserInfoSurvey> consumer) {
        super(payload, consumer);
    }
}
