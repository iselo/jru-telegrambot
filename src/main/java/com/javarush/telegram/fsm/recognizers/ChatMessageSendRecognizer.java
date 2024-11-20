package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.events.ChatMessageSendEvent;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.Instruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogModeState.CHAT;

@Immutable
public final class ChatMessageSendRecognizer extends CallbackQueryRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology) {
        var data = contentOf(update);
        if (context.dialogMode().state() == CHAT && data.startsWith("chat_")) {
            chronology.add(new Instruction() {
                @Override
                protected void execute(TelegramBotContext context) {
                    new ChatMessageSendEvent(Payload.of(data)).post();
                }
            });
            return true;
        }

        return false;
    }
}
