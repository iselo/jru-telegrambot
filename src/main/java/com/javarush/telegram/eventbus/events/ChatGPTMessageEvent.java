package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.eventbus.Observable;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Consumer;

@Builder
@Getter
public final class ChatGPTMessageEvent implements Observable {

    private final String payload;
    private final Consumer<String> consumer;
}
