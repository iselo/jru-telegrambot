package com.javarush.telegram.eventbus.handlers;

public interface EventHandler<T> {

    void handle(T event);
}
