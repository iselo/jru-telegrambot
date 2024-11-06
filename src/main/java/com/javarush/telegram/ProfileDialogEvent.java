package com.javarush.telegram;

import com.javarush.telegram.command.SendPhotoMessage;
import com.javarush.telegram.command.SendTextMessage;
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

            new SendPhotoMessage(KEYWORD).handle(bot, getChatId(update));

            String text = TelegramBotFileUtil.loadMessage(KEYWORD);
            new SendTextMessage(text).handle(bot, getChatId(update));
        }

        return false;
    }
}
