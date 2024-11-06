package com.javarush.telegram.command;

import com.javarush.telegram.MultiSessionTelegramBot;

import static org.glassfish.jersey.internal.guava.Preconditions.checkNotNull;

public abstract class Command<R> {

    public final R handle(MultiSessionTelegramBot bot, Long chatId) {
        checkNotNull(bot);
        checkNotNull(chatId);
        return execute(bot, chatId);
    }

    protected abstract R execute(MultiSessionTelegramBot bot, Long chatId);
}
