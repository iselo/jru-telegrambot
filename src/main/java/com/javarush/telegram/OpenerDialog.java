package com.javarush.telegram;

import com.javarush.telegram.command.SendPhotoMessage;
import com.javarush.telegram.command.SendTextMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

import static com.javarush.telegram.DialogMode.OPENER;

@Immutable
public final class OpenerDialog extends AbstractMessage {

    private static final String KEYWORD = "opener";

    public OpenerDialog(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(OPENER.toString())) {
            context().setMode(OPENER);
            context().resetQuestions();

            new SendPhotoMessage(KEYWORD).handle(bot, getChatId(update));
            String text = TelegramBotFileUtil.loadMessage(KEYWORD);
            new SendTextMessage(text).handle(bot, getChatId(update));
        }

        return false;
    }
}
