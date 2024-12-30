package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Observable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Immutable
@AllArgsConstructor
@Getter
public final class DateCelebrityMessageEvent implements Observable {

    private final String payload;
}
