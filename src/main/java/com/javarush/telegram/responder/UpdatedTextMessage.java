package com.javarush.telegram.responder;

import com.javarush.telegram.MultiSessionTelegramBot;
import com.javarush.telegram.TelegramBotException;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import javax.annotation.concurrent.Immutable;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Immutable
public final class UpdatedTextMessage extends RespondProcess<CompletableFuture<Serializable>> {

    private final Message message;

    private final String newText;

    public UpdatedTextMessage(Message message, String newText) {
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
