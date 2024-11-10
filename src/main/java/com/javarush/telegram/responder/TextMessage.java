package com.javarush.telegram.responder;

import com.javarush.telegram.MultiSessionTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.nio.charset.StandardCharsets;

import static com.google.common.base.Preconditions.checkNotNull;

public class TextMessage extends RespondProcess<Message> {

    protected final String text;

    public TextMessage(String text) {
        this.text = checkNotNull(text);
    }

    protected static SendMessage createTextMessage(String text, Long chatId) {
        var message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
        message.setChatId(chatId);

        return message;
    }

    @Override
    protected Message execute(MultiSessionTelegramBot bot, Long chatId) {
        var message = createTextMessage(text, chatId);
        return bot.customSendApiMethod(message);
    }
}
