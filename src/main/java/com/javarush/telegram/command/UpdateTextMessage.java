package com.javarush.telegram.command;

import com.javarush.telegram.MultiSessionTelegramBot;
import com.javarush.telegram.TelegramBotException;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Immutable
public final class UpdateTextMessage extends Command<CompletableFuture<Serializable>> {

    private final Message message;

    private final String newText;

    public UpdateTextMessage(Message message, String newText) {
        this.message = message;
        this.newText = newText;
    }

    @Override
    protected CompletableFuture<Serializable> execute(MultiSessionTelegramBot bot, Long chatId) {
        EditMessageText command = new EditMessageText();
        command.setChatId(message.getChatId());
        command.setMessageId(message.getMessageId());
        command.setText(newText);
        try {
            return bot.executeAsync(command);
        } catch (TelegramApiException e) {
            throw new TelegramBotException(e.getMessage());
        }
    }
}
