package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Observable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Immutable
@AllArgsConstructor
@Getter
public final class OpenerQuestionEvent implements Observable {

    private final Optional<String> payload;
}
