package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class CelebritySelectedEvent extends AbstractCallbackQuery {

    public CelebritySelectedEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {

        if (context().getMode() == DialogMode.DATE) {
            String data = update.getCallbackQuery().getData();

            if (data.startsWith("date_")) {
                sendPhotoMessage(bot, update, data);
                String prompt = TelegramBotFileUtil.loadPrompt(data);
                context().chatGPTService().setPrompt(prompt);
                return true;
            }
        }
        return false;
    }
}
