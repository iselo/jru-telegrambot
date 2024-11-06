package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class MessageNext extends AbstractMessage {

    public MessageNext(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {

        if (context().getMode() == DialogMode.MESSAGE) {
            String messageText = update.getMessage().getText();
            context().chatHistory().add(messageText);

            return true;
        }

        return false;
    }
}
