package com.javarush.telegram;

import javax.annotation.concurrent.Immutable;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public final class MessageNext extends AbstractMessage {

    public MessageNext(TelegramBotContext context) {
        super(context);
    }

    /**
     * @inheritDoc
     */
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
