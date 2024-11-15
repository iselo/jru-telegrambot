package com.javarush.telegram;

public final class TelegramBotException extends RuntimeException {

    public TelegramBotException(String message) {
        super(message);
    }
}
