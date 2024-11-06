package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractCallbackQuery
        extends AbstractMessageCore
        implements ResponseMessage {

    public AbstractCallbackQuery(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected final Long getChatId(Update update) {
        return update.getCallbackQuery().getFrom().getId();
    }

    @Override
    public final boolean apply(MultiSessionTelegramBot bot, Update update) {
        if (update.hasCallbackQuery()) {
            return handle(bot, update);
        }
        return false;
    }
}