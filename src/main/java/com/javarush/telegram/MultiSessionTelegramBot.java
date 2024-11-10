package com.javarush.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

public abstract class MultiSessionTelegramBot extends TelegramLongPollingBot {

    private final ThreadLocal<Update> updateEvent = new ThreadLocal<>();
    private final String botUserName;

    protected MultiSessionTelegramBot(String botUserName, String token) {
        super(token);
        this.botUserName = botUserName;
    }

    /**
     * Returns a username of this Telegram bot.
     */
    @Override
    public String getBotUsername() {
        return botUserName;
    }

    /**
     * Handles Telegram bot {@code Update} when even is occurred.
     */
    @Override
    public final void onUpdateReceived(Update updateEvent) {
        try {
            this.updateEvent.set(updateEvent);
            onUpdateEventReceived(this.updateEvent.get());
        } catch (Exception e) {
            throw new TelegramBotException(e.getMessage());
        }
    }

    /**
     * Handles execution of the message sending.
     */
    public <T extends Serializable, Method extends BotApiMethod<T>> T customSendApiMethod(Method message) { // NOSONAR
        try {
            return super.sendApiMethod(message);
        } catch (TelegramApiException e) {
            throw new TelegramBotException(e.getMessage());
        }
    }

    /**
     * Returns the chat id of given Update.
     *
     * @param update the Telegram bot chat Update instance
     * @return chat id
     */
    protected Long chatId(Update update) {

        if (update.hasMessage()) {
            return updateEvent.get().getMessage().getChatId();
        }

        if (update.hasCallbackQuery()) {
            return updateEvent.get().getCallbackQuery().getFrom().getId();
        }

        return null;
    }

    protected abstract void onUpdateEventReceived(Update updateEvent);
}
