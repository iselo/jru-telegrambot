package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Observable;
import com.javarush.telegram.responder.TextButtonsMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Immutable
@AllArgsConstructor
@Getter
public final class TextButtonsMessageEvent implements Observable {

    private final TextButtonsMessage payload;
}
