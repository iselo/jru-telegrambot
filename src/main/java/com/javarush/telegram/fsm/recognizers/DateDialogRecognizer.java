package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.events.DateDialogEvent;
import com.javarush.telegram.eventbus.events.DialogModeChangeEvent;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.Instruction;
import com.javarush.telegram.responder.Responder;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogModeState.DATE;

@Immutable
public final class DateDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology,
                             Responder responder) {
        if (contentOf(update).equalsIgnoreCase(DATE.toString())) {
            chronology.add(new Instruction() {
                @Override
                protected void execute(Responder responder, TelegramBotContext context) {
                    var eventBus = context.eventBus();
                    eventBus.post(new DialogModeChangeEvent(DATE));
                    eventBus.post(new DateDialogEvent(responder, context));
                }
            });
            return true;
        }

        return false;
    }
}
