package com.javarush.telegram.command;

import com.javarush.telegram.MultiSessionTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.nio.charset.StandardCharsets;

import static org.glassfish.jersey.internal.guava.Preconditions.checkNotNull;

public class SendTextMessage extends Command<Message> {

    protected final String text;

    public SendTextMessage(String text) {
        this.text = checkNotNull(text);
    }

    @Override
    protected Message execute(MultiSessionTelegramBot bot, Long chatId) {
        SendMessage message = createTextMessage(text, chatId);
        return bot.customSendApiMethod(message);
    }

    protected static SendMessage createTextMessage(String text, Long chatId) {
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
        message.setChatId(chatId);

        return message;
    }
}
