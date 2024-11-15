package com.javarush.telegram.fsm;

/**
 * Represents a recognised state of the Telegram bot start dialog finite state machine.
 */
public enum ModeFsmState {
    START,
    GPT_MESSAGE,
    PROFILE_QUESTION,
    OPENER_QUESTION,
    CHAT_MESSAGE_ADD,
    CHAT_MESSAGE_SEND,
    DATE_CELEBRITY_SELECT,
    DATE_CELEBRITY_MESSAGE,
    FINISH
}
