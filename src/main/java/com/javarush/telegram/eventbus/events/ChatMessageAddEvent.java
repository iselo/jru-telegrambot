package com.javarush.telegram.eventbus.events;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChatMessageAddEvent {

    private final String messageText;

    public ChatMessageAddEvent(String messageText) {
        this.messageText = checkNotNull(messageText);
    }

    @Override
    public String toString() {
        return messageText;
    }
}
