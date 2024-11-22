package com.javarush.telegram;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.eventbus.Subscribe;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.ChatDialogEvent;
import com.javarush.telegram.eventbus.events.ChatHistoryEvent;
import com.javarush.telegram.eventbus.events.ChatMessageAddEvent;

import java.util.ArrayList;
import java.util.List;

public final class ChatHistory implements Subscribable {

    private final List<String> history = new ArrayList<>();

    @Subscribe
    public void handle(ChatDialogEvent event) {
        history.clear();
    }

    @Subscribe
    public void handle(ChatMessageAddEvent event) {
        event.payload().ifPresent(history::add);
    }

    @Subscribe
    public void handle(ChatHistoryEvent event) {
        event.returnToConsumer(this.toString());
    }

    @Override
    @VisibleForTesting
    public String toString() {
        return String.join("\n\n", history);
    }

}
