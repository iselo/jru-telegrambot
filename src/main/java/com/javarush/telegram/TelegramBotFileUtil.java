package com.javarush.telegram;

import com.google.errorprone.annotations.Immutable;

import java.io.IOException;
import java.io.InputStream;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class TelegramBotFileUtil {

    private static final String TXT_EXTENSION = ".txt";
    private static final String JPG_EXTENSION = ".jpg";

    private TelegramBotFileUtil() {
        // Intentionally empty
    }

    public static String loadPrompt(String name) {
        try {
            var stream = ClassLoader.getSystemResourceAsStream("prompts/" + name + TXT_EXTENSION);
            checkNotNull(stream);
            return new String(stream.readAllBytes());
        } catch (IOException e) {
            throw new TelegramBotException("Can't load GPT prompt!");
        }
    }

    public static String loadMessage(String name) {
        try {
            var stream = ClassLoader.getSystemResourceAsStream("messages/" + name + TXT_EXTENSION);
            checkNotNull(stream);
            return new String(stream.readAllBytes());
        } catch (IOException e) {
            throw new TelegramBotException("Can't load message!");
        }
    }

    public static InputStream loadImage(String name) {
        try {
            return ClassLoader.getSystemResourceAsStream("images/" + name + JPG_EXTENSION);
        } catch (Exception e) {
            throw new TelegramBotException("Can't load photo!");
        }
    }
}
