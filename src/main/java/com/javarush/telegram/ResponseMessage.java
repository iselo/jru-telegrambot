package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

interface ResponseMessage {

    /**
     * Applies a response message to given update of Telegram bot.
     *
     * @param bot    Telegram bot instance
     * @param update Update instance
     * @return {@code true} if applying is completed, otherwise {@code false}
     */
    boolean apply(MultiSessionTelegramBot bot, Update update);
}
