package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import com.javarush.telegram.fsm.instructions.DialogModeInstruction;
import com.javarush.telegram.fsm.instructions.MenuInstruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogMode.START;

@Immutable
public final class BotStartDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        if (contentOf(update).equalsIgnoreCase(START.toString())) {
            fsmOutput.addInstruction(new DialogModeInstruction(START));
            fsmOutput.addInstruction(new MenuInstruction());
            return true;
        }

        return false;
    }
}
