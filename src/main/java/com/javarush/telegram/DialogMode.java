package com.javarush.telegram;

import com.google.common.eventbus.Subscribe;
import com.javarush.telegram.eventbus.events.ChatDialogEvent;
import com.javarush.telegram.eventbus.events.DateDialogEvent;
import com.javarush.telegram.eventbus.events.GptDialogEvent;
import com.javarush.telegram.eventbus.events.OpenerDialogEvent;
import com.javarush.telegram.eventbus.events.ProfileDialogEvent;
import com.javarush.telegram.eventbus.events.StartDialogEvent;

import static com.javarush.telegram.DialogModeState.CHAT;
import static com.javarush.telegram.DialogModeState.DATE;
import static com.javarush.telegram.DialogModeState.GPT;
import static com.javarush.telegram.DialogModeState.OPENER;
import static com.javarush.telegram.DialogModeState.PROFILE;
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

    @Subscribe
    public void handle(StartDialogEvent event) {
        this.state = START;
    }

    @Subscribe
    public void handle(GptDialogEvent event) {
        this.state = GPT;
    }

    @Subscribe
    public void handle(ProfileDialogEvent event) {
        this.state = PROFILE;
    }

    @Subscribe
    public void handle(OpenerDialogEvent event) {
        this.state = OPENER;
    }

    @Subscribe
    public void handle(ChatDialogEvent event) {
        this.state = CHAT;
    }

    @Subscribe
    public void handle(DateDialogEvent event) {
        this.state = DATE;
    }

}
