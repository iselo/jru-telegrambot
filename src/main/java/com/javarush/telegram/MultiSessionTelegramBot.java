package com.javarush.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

public abstract class MultiSessionTelegramBot extends TelegramLongPollingBot {

    private final String name;
    private final String token;

    private final ThreadLocal<Update> updateEvent = new ThreadLocal<>();

    protected MultiSessionTelegramBot(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public <T extends Serializable, Method extends BotApiMethod<T>> T customSendApiMethod(Method message) {
        try {
            return super.sendApiMethod(message);
        } catch (TelegramApiException e) {
            throw new TelegramBotException(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public final void onUpdateReceived(Update updateEvent) {
        try {
            this.updateEvent.set(updateEvent);
            onUpdateEventReceived(this.updateEvent.get());
        } catch (Exception e) {
            throw new TelegramBotException(e.getMessage());
        }
    }

    public abstract void onUpdateEventReceived(Update updateEvent) throws Exception;

}
