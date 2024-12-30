package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.eventbus.Observable;
import com.javarush.telegram.survey.UserInfoSurvey;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Consumer;

@AllArgsConstructor
@Getter
public final class SurveyEvent implements Observable {

    private final Consumer<UserInfoSurvey> consumer;
}
