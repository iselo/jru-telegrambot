package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

import static com.javarush.telegram.DialogMode.OPENER;

@Immutable
public final class OpenerDialogEvent extends AbstractMessage {

    private static final String KEYWORD = "opener";

    public OpenerDialogEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(OPENER.toString())) {
            context().setMode(OPENER);
            context().resetQuestions();

            sendPhotoMessage(bot, update, KEYWORD);
            String text = TelegramBotFileUtil.loadMessage(KEYWORD);
            sendTextMessage(bot, update, text);
        }

        return false;
    }
}
