package com.javarush.telegram;

import com.google.common.eventbus.EventBus;
import com.javarush.telegram.eventbus.events.ChatHistoryClearEvent;
import com.javarush.telegram.eventbus.events.ChatMessageAddEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChatHistoryTest {

    private EventBus eventBus;
    private ChatHistory chatHistory;

    @BeforeEach
    void setUp() {
        eventBus = new EventBus("test");
        chatHistory = new ChatHistory();

        eventBus.register(chatHistory);
    }

    @Test
    @DisplayName("")
    void emptyIfNew() {
        assertEquals(chatHistory.toString(), "");
    }

    @Test
    @DisplayName("Adds message to chat history on ChatMessageAddEvent")
    void addsMessage() {
        var sample_message = new ChatMessageAddEvent("Sample Message");
        eventBus.post(sample_message);
        eventBus.post(sample_message);

        assertEquals(chatHistory.toString(), "Sample Message\n\nSample Message");
    }

    @Test
    void clearsContent() {
        eventBus.post(new ChatMessageAddEvent("Sample Message"));
        eventBus.post(new ChatHistoryClearEvent());

        assertEquals(chatHistory.toString(), "");
    }
}