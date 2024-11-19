package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.DialogModeState;

import static com.google.common.base.Preconditions.checkNotNull;

public class DialogModeChangeEvent {

    private final DialogModeState state;

    public DialogModeChangeEvent(DialogModeState mode) {
        this.state = checkNotNull(mode);
    }

    public DialogModeState state() {
        return state;
    }
}
