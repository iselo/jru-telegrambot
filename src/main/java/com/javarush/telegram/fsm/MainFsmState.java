package com.javarush.telegram.fsm;

/**
 * Represents a recognised state of the GPT dialog finite state machine.
 */
public enum MainFsmState {
    START,
    BOT_MENU,
    BOT_MODE,
    FINISH
}
