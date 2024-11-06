package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

import static com.javarush.telegram.DialogMode.GPT;

@Immutable
public final class GptDialogEvent extends AbstractMessage {

    public GptDialogEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(GPT.toString())) {
            context().setMode(GPT);

            sendPhotoMessage(bot, update, "gpt");
            String text = TelegramBotFileUtil.loadMessage("gpt");
            sendTextMessage(bot, update, text);

            return true;
        }

        return false;
    }
}
