package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import com.javarush.telegram.fsm.instructions.DialogModeInstruction;
import com.javarush.telegram.fsm.instructions.GptDialogInstruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogMode.GPT;

@Immutable
public final class GptDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        if (contentOf(update).equalsIgnoreCase(GPT.toString())) {
            fsmOutput
                    .addInstruction(new DialogModeInstruction(GPT))
                    .addInstruction(new GptDialogInstruction());

            return true;
        }

        return false;
    }
}
