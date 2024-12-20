package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.events.ChatDialogEvent;
import com.javarush.telegram.fsm.Chronology;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogModeState.CHAT;

@Immutable
public final class ChatDialogRecognizer implements MessageRecognizer {

    @Override
    public boolean handle(Update update, TelegramBotContext context, Chronology chronology) {
        if (contentOf(update).equalsIgnoreCase(CHAT.toString())) {
            chronology.add(() -> new ChatDialogEvent().post());
            return true;
        }
        return false;
    }
}
