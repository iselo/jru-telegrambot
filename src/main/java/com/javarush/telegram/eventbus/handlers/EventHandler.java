package com.javarush.telegram.eventbus.handlers;

import com.google.errorprone.annotations.Immutable;

@Immutable(containerOf = "T")
public interface EventHandler<T> {

    void handle(T event);
}
