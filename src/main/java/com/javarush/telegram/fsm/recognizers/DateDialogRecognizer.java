package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import com.javarush.telegram.fsm.instructions.DateDialogInstruction;
import com.javarush.telegram.fsm.instructions.DialogModeInstruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogMode.DATE;

@Immutable
public final class DateDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        if (contentOf(update).equalsIgnoreCase(DATE.toString())) {
            fsmOutput
                    .addInstruction(new DialogModeInstruction(DATE))
                    .addInstruction(new DateDialogInstruction());

            return true;
        }

        return false;
    }
}
