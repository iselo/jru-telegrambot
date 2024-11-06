package com.javarush.telegram;

/**
 * Represents Telegram dialog mode.
 */
public enum DialogMode {

    START("/start"),
    PROFILE("/profile"),
    OPENER("/opener"),
    MESSAGE("/message"),
    DATE("/date"),
    GPT("/gpt");

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
