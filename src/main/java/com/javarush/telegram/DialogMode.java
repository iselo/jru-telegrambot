package com.javarush.telegram;

import com.google.common.eventbus.Subscribe;
import com.javarush.telegram.eventbus.events.DialogModeChangeEvent;

import static com.javarush.telegram.DialogModeState.START;

/**
 * Represents Telegram bot dialog mode state.
 */
public final class DialogMode {

    private DialogModeState state = START;

    /**
     * Returns current Telegram bot dialog mode state.
     */
    public DialogModeState state() {
        return state;
    }

    /**
     * Sets current Telegram bot dialog mode.
     */
    @Subscribe
    public void onDialogModeChange(DialogModeChangeEvent event) {
        this.state = event.state();
    }

}
