package com.javarush.telegram;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class TelegramBotException extends RuntimeException {
    public TelegramBotException(String message) {
        super(message);
    }
}
