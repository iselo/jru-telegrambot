package com.javarush.telegram;

import com.google.common.eventbus.Subscribe;
import com.javarush.telegram.eventbus.events.ChatHistoryClearEvent;
import com.javarush.telegram.eventbus.events.ChatMessageAddEvent;

import java.util.ArrayList;
import java.util.List;

public final class ChatHistory {

    private final List<String> history = new ArrayList<>();

    @Subscribe
    public void onChatHistoryClear(ChatHistoryClearEvent event) {
        history.clear();
    }

    @Subscribe
    public void onChatMassageAdd(ChatMessageAddEvent event) {
        history.add(event.toString());
    }

    @Override
    public String toString() {
        return String.join("\n\n", history);
    }

}
