package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.events.DialogModeChangeEvent;
import com.javarush.telegram.eventbus.events.GptDialogEvent;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.Instruction;
import com.javarush.telegram.responder.Responder;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogModeState.GPT;

@Immutable
public final class GptDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology,
                             Responder responder) {
        if (contentOf(update).equalsIgnoreCase(GPT.toString())) {
            chronology.add(new Instruction() {
                @Override
                protected void execute(Responder responder, TelegramBotContext context) {
                    var eventBus = context.eventBus();
                    eventBus.post(new DialogModeChangeEvent(GPT));
                    eventBus.post(new GptDialogEvent(responder, context));
                }
            });

            return true;
        }

        return false;
    }
}
