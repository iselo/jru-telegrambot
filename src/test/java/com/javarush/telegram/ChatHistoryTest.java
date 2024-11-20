package com.javarush.telegram;

import com.google.common.eventbus.EventBus;
import com.google.common.testing.NullPointerTester;
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
    @DisplayName("New chat history is empty")
    void emptyIfNew() {
        assertEquals(chatHistory.toString(), "");
    }

    @Test
    @DisplayName("Public methods refuses null")
    void refusesNull() {
        var nullPointerTester = new NullPointerTester();
        nullPointerTester.testAllPublicConstructors(ChatHistory.class);
        nullPointerTester.testAllPublicStaticMethods(ChatHistory.class);
    }
}