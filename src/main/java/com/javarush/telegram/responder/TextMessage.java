package com.javarush.telegram.responder;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.MultiSessionTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.nio.charset.StandardCharsets.UTF_8;

@Immutable
public class TextMessage extends RespondProcess<Message> {

    private final String text;

    public TextMessage(String text) {
        this.text = checkNotNull(text);
    }

    protected static SendMessage createTextMessage(String text, Long chatId) {
        var message = new SendMessage();
        message.setText(new String(text.getBytes(UTF_8), UTF_8));
        message.setParseMode("markdown");
        message.setChatId(chatId);

        return message;
    }

    protected String text() {
        return text;
    }

    @Override
    @CanIgnoreReturnValue
    protected Message execute(MultiSessionTelegramBot bot, Long chatId) {
        var message = createTextMessage(text, chatId);
        return bot.customSendApiMethod(message);
    }
}
