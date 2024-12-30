package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.eventbus.Observable;
import com.javarush.telegram.responder.TextMessage;
import lombok.Builder;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Consumer;

@Builder
@Getter
public final class TextMessageEvent implements Observable {

    private final TextMessage payload;
    private final Consumer<Message> consumer;
}
