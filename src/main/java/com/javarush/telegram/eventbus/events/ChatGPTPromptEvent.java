package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.eventbus.Payload;

public class ChatGPTPromptEvent extends Event<Payload<String>> {

    public ChatGPTPromptEvent(Payload<String> payload) {
        super(payload);
    }
}
