package com.javarush.telegram;

/**
 * Represents Telegram dialog mode.
 */
public enum DialogModeState {

    START("/start"),
    GPT("/gpt"),
    PROFILE("/profile"),
    OPENER("/opener"),
    CHAT("/chat"),
    DATE("/date");

    private final String modeName;

    DialogModeState(String modeName) {
        this.modeName = modeName;
    }

    @Override
    public String toString() {
        return modeName;
    }
}
