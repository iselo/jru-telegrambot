package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractMessage extends AbstractMessageCore implements ResponseMessage {

    protected AbstractMessage(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected final Long getChatId(Update update) {
        return update.getMessage().getFrom().getId();
    }

    @Override
    public final boolean apply(MultiSessionTelegramBot bot, Update update) {
        if (update.hasMessage()) {
            return handle(bot, update);
        }
        return false;
    }
}
