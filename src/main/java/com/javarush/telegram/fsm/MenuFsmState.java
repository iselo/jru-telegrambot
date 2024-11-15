package com.javarush.telegram.fsm;

/**
 * Represents a recognised state of the Telegram bot start dialog finite state machine.
 */
public enum MenuFsmState {
    START,
    MENU,
    GPT_DIALOG,
    PROFILE_DIALOG,
    OPENER_DIALOG,
    CHAT_DIALOG,
    DATE_DIALOG,
    FINISH
}
