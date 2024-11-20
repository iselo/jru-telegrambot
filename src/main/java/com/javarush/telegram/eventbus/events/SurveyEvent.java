package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.survey.UserInfoSurvey;

import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

public class SurveyEvent extends Event<Payload<String>> {

    @SuppressWarnings("Immutable")
    private final Consumer<UserInfoSurvey> consumer;

    public SurveyEvent(Payload<String> payload) {
        super(payload);
        this.consumer = null;
    }

    public SurveyEvent(Payload<String> payload, Consumer<UserInfoSurvey> consumer) {
        super(payload);
        this.consumer = checkNotNull(consumer);
    }

    public Consumer<UserInfoSurvey> consumer() {
        return consumer;
    }
}
