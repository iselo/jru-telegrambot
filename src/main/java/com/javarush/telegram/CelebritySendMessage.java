package com.javarush.telegram;

import com.javarush.telegram.command.SendTextMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class CelebritySendMessage extends AbstractMessage {

    public CelebritySendMessage(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {

        if (context().getMode() == DialogMode.DATE) {
            String messageText = update.getMessage().getText();
            String answer = context().chatGPTService().addMessage(messageText);
            new SendTextMessage(answer).handle(bot, getChatId(update));
            return true;
        }
        return false;
    }
}
