package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class TelegramBotHelper {

    private TelegramBotHelper() {
        // Intentionally empty
    }

    public static SendMessage createTextMessage(String text, Long chatId) {
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
        message.setChatId(chatId);

        return message;
    }

    public static SendPhoto createPhotoMessage(Long chatId, String pictureName) {
        InputFile inputFile = new InputFile();
        InputStream mediaStream = TelegramBotFileUtil.loadImage(pictureName);
        inputFile.setMedia(mediaStream, pictureName);

        SendPhoto photo = new SendPhoto();
        photo.setPhoto(inputFile);
        photo.setChatId(chatId);
        return photo;
    }

    public static void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (Map.Entry<String,String> entry : buttons.entrySet()) {
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
}
