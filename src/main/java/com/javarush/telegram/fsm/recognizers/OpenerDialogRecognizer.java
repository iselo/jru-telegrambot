package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import com.javarush.telegram.fsm.instructions.DialogModeInstruction;
import com.javarush.telegram.fsm.instructions.OpenerDialogInstruction;
import com.javarush.telegram.fsm.instructions.OpenerQuestionInstruction;
import com.javarush.telegram.fsm.instructions.QuestionsResetInstruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.javarush.telegram.DialogMode.OPENER;

@Immutable
public final class OpenerDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        if (contentOf(update).equalsIgnoreCase(OPENER.toString())) {
            var noPreviousAnswer = Optional.<String>empty();

            fsmOutput
                    .addInstruction(new DialogModeInstruction(OPENER))
                    .addInstruction(new OpenerDialogInstruction())
                    .addInstruction(new QuestionsResetInstruction())
                    .addInstruction(new OpenerQuestionInstruction(noPreviousAnswer));

            return true;
        }

        return false;
    }
}
