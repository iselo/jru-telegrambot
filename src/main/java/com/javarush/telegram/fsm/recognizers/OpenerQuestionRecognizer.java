package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import com.javarush.telegram.fsm.instructions.OpenerQuestionInstruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.javarush.telegram.DialogMode.OPENER;

@Immutable
public final class OpenerQuestionRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        if (context.getMode() == OPENER && context.survey().questions().isPresent()) {
            var messageText = contentOf(update);
            var previousAnswer = Optional.of(messageText);

            fsmOutput.addInstruction(new OpenerQuestionInstruction(previousAnswer));

            return true;
        }

        return false;
    }
}
