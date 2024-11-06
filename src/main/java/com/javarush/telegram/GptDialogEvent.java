package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class GptDialogEvent extends AbstractMessage {

    private final static String EVENT = "/gpt";

    public GptDialogEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(EVENT)) {
            context().setMode(DialogMode.GPT);

            sendPhotoMessage(bot, update, "gpt");
            String text = TelegramBotFileUtil.loadMessage("gpt");
            sendTextMessage(bot, update, text);

            return true;
        }

        return false;
    }
}
