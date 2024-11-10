package com.javarush.telegram;

/**
 * Represents Telegram dialog mode.
 */
public enum DialogMode {

    START("/start"),
    GPT("/gpt"),
    PROFILE("/profile"),
    OPENER("/opener"),
    CHAT("/chat"),
    DATE("/date");

    private final String modeName;

    DialogMode(String modeName) {
        this.modeName = modeName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return modeName;
    }
}
