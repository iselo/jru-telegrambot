package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.events.OpenerDialogEvent;
import com.javarush.telegram.eventbus.events.OpenerQuestionEvent;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.Instruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.javarush.telegram.DialogModeState.OPENER;

@Immutable
public final class OpenerDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology) {
        if (contentOf(update).equalsIgnoreCase(OPENER.toString())) {
            chronology.add(new Instruction() {
                @Override
                protected void execute(TelegramBotContext context) {
                    new OpenerDialogEvent(Payload.ofEmpty()).post();
                    var noPreviousAnswer = Optional.<String>empty();
                    new OpenerQuestionEvent(Payload.of(noPreviousAnswer)).post();
                }
            });

            return true;
        }

        return false;
    }
}
