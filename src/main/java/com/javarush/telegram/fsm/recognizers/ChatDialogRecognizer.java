package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.events.ChatDialogEvent;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.Instruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogModeState.CHAT;

@Immutable
public final class ChatDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology) {
        if (contentOf(update).equalsIgnoreCase(CHAT.toString())) {
            chronology.add(new Instruction() {
                @Override
                protected void execute(TelegramBotContext context) {
                    new ChatDialogEvent(Payload.ofEmpty()).post();
                }
            });

            return true;
        }

        return false;
    }
}
