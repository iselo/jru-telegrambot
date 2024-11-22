package com.javarush.telegram;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.javarush.telegram.DialogModeState.CHAT;
import static com.javarush.telegram.DialogModeState.DATE;
import static com.javarush.telegram.DialogModeState.GPT;
import static com.javarush.telegram.DialogModeState.OPENER;
import static com.javarush.telegram.DialogModeState.PROFILE;
import static com.javarush.telegram.DialogModeState.START;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DialogModeTest {

    @ParameterizedTest
    @DisplayName("Modes has correct string representation")
    @MethodSource("testCases")
    void hasCorrectStringRepresentation(DialogModeState state, String stringRepresentation) {
        assertEquals(state.toString(), stringRepresentation);
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(START, "/start"),
                Arguments.of(GPT, "/gpt"),
                Arguments.of(PROFILE, "/profile"),
                Arguments.of(OPENER, "/opener"),
                Arguments.of(CHAT, "/chat"),
                Arguments.of(DATE, "/date")
        );
    }
}
