package com.javarush.telegram.responder;

import static com.google.common.base.Preconditions.checkNotNull;

import com.javarush.telegram.MultiSessionTelegramBot;


public abstract class RespondProcess<R> {

    public final R handle(MultiSessionTelegramBot bot, Long chatId) {
        checkNotNull(bot);
        checkNotNull(chatId);
        return execute(bot, chatId);
    }

    protected abstract R execute(MultiSessionTelegramBot bot, Long chatId);
}
