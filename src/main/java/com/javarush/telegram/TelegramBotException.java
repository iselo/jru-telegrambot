package com.javarush.telegram;

import com.google.errorprone.annotations.Immutable;

@Immutable
public final class TelegramBotException extends RuntimeException {

    public TelegramBotException(String message) {
        super(message);
    }
}
