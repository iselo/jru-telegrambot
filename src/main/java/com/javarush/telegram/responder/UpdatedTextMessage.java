package com.javarush.telegram.responder;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.MultiSessionTelegramBot;
import com.javarush.telegram.TelegramBotException;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class UpdatedTextMessage extends RespondProcess<CompletableFuture<Serializable>> {

    @SuppressWarnings("Immutable")
    private final Message message;

    private final String newText;

    public UpdatedTextMessage(Message message, String newText) {
        this.message = checkNotNull(message);
        this.newText = checkNotNull(newText);
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
