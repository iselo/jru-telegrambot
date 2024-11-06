package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

import static com.javarush.telegram.DialogMode.PROFILE;

@Immutable
public final class ProfileDialogEvent extends AbstractMessage {

    private static final String KEYWORD = "profile";

    public ProfileDialogEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(PROFILE.toString())) {
            context().setMode(PROFILE);
            context().resetQuestions();

            sendPhotoMessage(bot, update, KEYWORD);
            String text = TelegramBotFileUtil.loadMessage(KEYWORD);
            sendTextMessage(bot, update, text);
        }

        return false;
    }
}
