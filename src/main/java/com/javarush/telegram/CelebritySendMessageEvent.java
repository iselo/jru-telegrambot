package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class CelebritySendMessageEvent extends AbstractMessage {

    public CelebritySendMessageEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {

        if (context().getMode() == DialogMode.DATE) {
            String messageText = update.getMessage().getText();
            String answer = context().chatGPTService().addMessage(messageText);
            sendTextMessage(bot, update, answer);
            return true;
        }
        return false;
    }
}
