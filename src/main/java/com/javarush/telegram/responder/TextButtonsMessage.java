package com.javarush.telegram.responder;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.MultiSessionTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class TextButtonsMessage extends TextMessage {

    private final Map<String, String> buttons;

    public TextButtonsMessage(String text, Map<String, String> buttons) {
        super(text);
        this.buttons = checkNotNull(buttons);
    }

    private static void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (Map.Entry<String, String> entry : buttons.entrySet()) {
            String buttonName = entry.getKey();
            String buttonValue = entry.getValue();

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);

            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    @Override
    protected Message execute(MultiSessionTelegramBot bot, Long chatId) {
        SendMessage message = createTextMessage(text, chatId);
        if (!buttons.isEmpty()) {
            attachButtons(message, buttons);
        }
        return bot.customSendApiMethod(message);
    }
}
