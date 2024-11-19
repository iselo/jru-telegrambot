package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class ChatMessageAddEvent {

    private final String messageText;

    public ChatMessageAddEvent(String messageText) {
        this.messageText = checkNotNull(messageText);
    }

    @Override
    public String toString() {
        return messageText;
    }
}
