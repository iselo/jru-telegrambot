package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Observable;
import com.javarush.telegram.responder.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Immutable
@AllArgsConstructor
@Getter
public final class MenuEvent implements Observable {

    private final Menu payload;
}
