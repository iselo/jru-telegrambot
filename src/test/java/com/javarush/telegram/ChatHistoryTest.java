package com.javarush.telegram;

import com.google.common.testing.NullPointerTester;
import com.javarush.telegram.eventbus.events.ChatDialogEvent;
import com.javarush.telegram.eventbus.events.ChatHistoryEvent;
import com.javarush.telegram.eventbus.events.ChatMessageAddEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChatHistoryTest {

    private ChatHistory chatHistory;

    @BeforeEach
    void setUp() {
        chatHistory = new ChatHistory();
        chatHistory.register();
    }

    @Test
    @DisplayName("New chat history is empty")
    void emptyIfNew() {
        assertEquals("", chatHistory.toString());
    }

    @Test
    @DisplayName("Adds message to chat history")
    void addsMessageToChatHistory() {
        new ChatMessageAddEvent("Message sample").post();
        assertEquals("Message sample", chatHistory.toString());
    }

    @Test
    @DisplayName("Clears chat history")
    void clearsChatHistory() {
        new ChatMessageAddEvent("Message sample").post();
        new ChatDialogEvent().post();
        assertEquals("", chatHistory.toString());
    }

    @Test
    void returnsChatHistory() {
        new ChatMessageAddEvent("Message sample").post();
        AtomicReference<String> history = new AtomicReference<>();
        new ChatHistoryEvent(history::set).post();
        assertEquals("Message sample", history.get());
    }

    @Test
    @DisplayName("Public methods refuses null")
    void refusesNull() {
        var nullPointerTester = new NullPointerTester();
        nullPointerTester.testAllPublicConstructors(ChatHistory.class);
        nullPointerTester.testAllPublicStaticMethods(ChatHistory.class);
    }
}