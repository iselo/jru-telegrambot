package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.eventbus.Observable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Consumer;

@AllArgsConstructor
@Getter
public final class ChatHistoryEvent implements Observable {

    private final Consumer<String> consumer;
}
