package com.javarush.telegram;

import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class TelegramBotFileUtil {

    private static final String EXTENTION = ".txt";

    private TelegramBotFileUtil() {
        // Intentionally empty
    }

    public static String loadPrompt(String name) {
        try {
            var is = ClassLoader.getSystemResourceAsStream("prompts/" + name + EXTENTION);
            return new String(is.readAllBytes());
        } catch (IOException e) {
            throw new TelegramBotException("Can't load GPT prompt!");
        }
    }

    public static String loadMessage(String name) {
        try {
            InputStream stream = ClassLoader.getSystemResourceAsStream("messages/" + name + EXTENTION);
            return new String(stream.readAllBytes());
        } catch (IOException e) {
            throw new TelegramBotException("Can't load message!");
        }
    }

    public static InputStream loadImage(String name) {
        try {
            return ClassLoader.getSystemResourceAsStream("images/" + name + ".jpg");
        } catch (Exception e) {
            throw new TelegramBotException("Can't load photo!");
        }
    }
}
