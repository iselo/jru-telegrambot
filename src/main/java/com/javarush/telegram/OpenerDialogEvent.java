package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OpenerDialogEvent extends AbstractMessage {

    private final static String EVENT_KEYWORD = "opener";
    private final static String EVENT = "/" + EVENT_KEYWORD;

    public OpenerDialogEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(EVENT)) {
            context().setMode(DialogMode.OPENER);
            context().resetQuestions();

            sendPhotoMessage(bot, update, EVENT_KEYWORD);
            String text = TelegramBotFileUtil.loadMessage(EVENT_KEYWORD);
            sendTextMessage(bot, update, text);
        }

        return false;
    }
}
