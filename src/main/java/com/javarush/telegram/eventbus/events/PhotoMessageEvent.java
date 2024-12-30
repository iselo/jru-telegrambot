package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Observable;
import com.javarush.telegram.responder.PhotoMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Immutable
@AllArgsConstructor
@Getter
public final class PhotoMessageEvent implements Observable {

    private final PhotoMessage payload;
}
