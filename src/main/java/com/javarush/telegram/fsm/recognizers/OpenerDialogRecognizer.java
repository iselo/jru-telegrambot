package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.events.DialogModeChangeEvent;
import com.javarush.telegram.eventbus.events.OpenerDialogEvent;
import com.javarush.telegram.eventbus.events.OpenerQuestionEvent;
import com.javarush.telegram.eventbus.events.SurveyQuestionsResetEvent;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.Instruction;
import com.javarush.telegram.responder.Responder;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.javarush.telegram.DialogModeState.OPENER;

@Immutable
public final class OpenerDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology,
                             Responder responder) {
        if (contentOf(update).equalsIgnoreCase(OPENER.toString())) {
            chronology.add(new Instruction() {
                @Override
                protected void execute(Responder responder, TelegramBotContext context) {
                    var eventBus = context.eventBus();
                    eventBus.post(new DialogModeChangeEvent(OPENER));
                    eventBus.post(new OpenerDialogEvent(responder, context));
                    eventBus.post(new SurveyQuestionsResetEvent());
                    var noPreviousAnswer = Optional.<String>empty();
                    eventBus.post(new OpenerQuestionEvent(responder, context, noPreviousAnswer));
                }
            });

            return true;
        }

        return false;
    }
}
