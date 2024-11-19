package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.events.ChatMessageSendEvent;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.Instruction;
import com.javarush.telegram.responder.Responder;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogModeState.CHAT;

@Immutable
public final class ChatMessageSendRecognizer extends CallbackQueryRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology,
                             Responder responder) {
        var data = contentOf(update);
        if (context.dialogMode().state() == CHAT && data.startsWith("chat_")) {
            chronology.add(new Instruction() {
                @Override
                protected void execute(Responder responder, TelegramBotContext context) {
                    context.eventBus().post(new ChatMessageSendEvent(responder, context, data));
                }
            });
            return true;
        }

        return false;
    }
}
