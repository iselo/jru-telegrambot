package com.javarush.telegram;

public enum DialogMode {

    START("/start"),
    PROFILE("/profile"),
    OPENER("/opener"),
    MESSAGE("/message"),
    DATE("/date"),
    GPT("/gpt");

    private String modeName;

    DialogMode(String modeName) {
        this.modeName = modeName;
    }

    @Override
    public String toString() {
        return modeName;
    }
}
