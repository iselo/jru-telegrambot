package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import com.javarush.telegram.fsm.instructions.DialogModeInstruction;
import com.javarush.telegram.fsm.instructions.ProfileDialogInstruction;
import com.javarush.telegram.fsm.instructions.ProfileQuestionInstruction;
import com.javarush.telegram.fsm.instructions.QuestionsResetInstruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.javarush.telegram.DialogMode.PROFILE;

@Immutable
public final class ProfileDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        if (contentOf(update).equalsIgnoreCase(PROFILE.toString())) {
            var noPreviousAnswer = Optional.<String>empty();

            fsmOutput
                    .addInstruction(new DialogModeInstruction(PROFILE))
                    .addInstruction(new ProfileDialogInstruction())
                    .addInstruction(new QuestionsResetInstruction())
                    .addInstruction(new ProfileQuestionInstruction(noPreviousAnswer));

            return true;
        }

        return false;
    }
}
