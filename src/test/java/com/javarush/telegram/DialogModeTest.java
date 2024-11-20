package com.javarush.telegram;

import com.google.common.eventbus.EventBus;
import com.javarush.telegram.eventbus.events.DialogModeChangeEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javarush.telegram.DialogModeState.START;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DialogModeTest {

    @Test
    @DisplayName("Sets mode on DialogModeChangeEvent")
    void setsMode() {
        var eventBus = new EventBus("test");
        var dialogMode = new DialogMode();
        eventBus.register(dialogMode);
        eventBus.post(new DialogModeChangeEvent(START));

        assertEquals(dialogMode.state(), START);
    }
}