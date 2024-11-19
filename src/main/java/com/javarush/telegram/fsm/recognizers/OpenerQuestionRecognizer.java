package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.events.OpenerQuestionEvent;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.Instruction;
import com.javarush.telegram.responder.Responder;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.javarush.telegram.DialogModeState.OPENER;

@Immutable
public final class OpenerQuestionRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology,
                             Responder responder) {
        if (context.dialogMode().state() == OPENER && context.survey().questions().isPresent()) {
            var messageText = contentOf(update);
            var previousAnswer = Optional.of(messageText);

            chronology.add(new Instruction() {
                @Override
                protected void execute(Responder responder, TelegramBotContext context) {
                    context.eventBus().post(new OpenerQuestionEvent(responder, context, previousAnswer));
                }
            });

            return true;
        }

        return false;
    }
}
