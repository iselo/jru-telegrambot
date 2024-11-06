package com.javarush.telegram;

import com.javarush.telegram.command.SendPhotoMessage;
import com.javarush.telegram.command.SendTextMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

import static com.javarush.telegram.DialogMode.GPT;

@Immutable
public final class GptDialogEvent extends AbstractMessage {

    private final static String KEYWORD = "gpt";

    public GptDialogEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(GPT.toString())) {
            context().setMode(GPT);

            new SendPhotoMessage(KEYWORD).handle(bot, getChatId(update));
            String text = TelegramBotFileUtil.loadMessage(KEYWORD);
            new SendTextMessage(text).handle(bot, getChatId(update));
            return true;
        }

        return false;
    }
}
