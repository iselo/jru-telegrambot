package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractMessageCore {

    private final TelegramBotContext context;

    protected AbstractMessageCore(TelegramBotContext context) {
        this.context = context;
    }

    /**
     * Returns Telegram bot context.
     *
     * @return gram bot context
     */
    public final TelegramBotContext context() {
        return context;
    }

    /**
     * Handles a response message
     *
     * @param bot    Telegram bot instance
     * @param update chat Update instanc
     * @return {@code true} if handling is completed, otherwise {@code false}
     */

    protected abstract boolean handle(MultiSessionTelegramBot bot, Update update);

    /**
     * Returns the chat id of given Update.
     *
     * @param update Update instance
     * @return chat id
     */
    protected abstract Long getChatId(Update update);
}
