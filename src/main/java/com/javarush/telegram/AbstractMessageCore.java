package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

public abstract class AbstractMessageCore {

    private final TelegramBotContext context;

    public AbstractMessageCore(TelegramBotContext context) {
        this.context = context;
    }

    public final TelegramBotContext context() {
        return context;
    }

    protected final Message sendTextMessage(MultiSessionTelegramBot bot, Update update, String text) {
        Long chatId = getChatId(update);
        SendMessage message = TelegramBotHelper.createTextMessage(text, chatId);
        return bot.customSendApiMethod(message);
    }

    protected final void sendPhotoMessage(MultiSessionTelegramBot bot, Update update, String pictureName) {
        Long chatId = getChatId(update);
        SendPhoto photo = TelegramBotHelper.createPhotoMessage(chatId, pictureName);
        try {
            bot.execute(photo);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected final void sendTextButtonsMessage(MultiSessionTelegramBot bot, Update update, String
            text, Map<String, String> buttons) {
        Long chatId = getChatId(update);
        SendMessage message = TelegramBotHelper.createTextMessage(text, chatId);
        if (!buttons.isEmpty())
            TelegramBotHelper.attachButtons(message, buttons);
        bot.customSendApiMethod(message);
    }

    protected final void updateTextMessage(MultiSessionTelegramBot bot, Update update, Message message, String text) {
        EditMessageText command = new EditMessageText();
        command.setChatId(message.getChatId());
        command.setMessageId(message.getMessageId());
        command.setText(text);
        try {
            bot.executeAsync(command);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract boolean handle(MultiSessionTelegramBot bot, Update update);

    protected abstract Long getChatId(Update update);
}
