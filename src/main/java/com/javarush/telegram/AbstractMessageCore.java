package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractMessageCore {

    private final TelegramBotContext context;

    protected AbstractMessageCore(TelegramBotContext context) {
        this.context = context;
    }

    public final TelegramBotContext context() {
        return context;
    }

    protected abstract boolean handle(MultiSessionTelegramBot bot, Update update);

    protected abstract Long getChatId(Update update);
}
