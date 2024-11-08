package com.javarush.telegram.responder;

import static org.glassfish.jersey.internal.guava.Preconditions.checkNotNull;

import com.javarush.telegram.MultiSessionTelegramBot;
import java.nio.charset.StandardCharsets;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class TextMessage extends RespondProcess<Message> {

    protected final String text;

    public TextMessage(String text) {
        this.text = checkNotNull(text);
    }

    protected static SendMessage createTextMessage(String text, Long chatId) {
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
        message.setChatId(chatId);

        return message;
    }

    @Override
    protected Message execute(MultiSessionTelegramBot bot, Long chatId) {
        SendMessage message = createTextMessage(text, chatId);
        return bot.customSendApiMethod(message);
    }
}
