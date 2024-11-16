package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import com.javarush.telegram.fsm.instructions.GptMessageInstruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogMode.GPT;

@Immutable
public final class GptMessageRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        if (context.getMode() == GPT) {
            var text = contentOf(update);
            fsmOutput.addInstruction(new GptMessageInstruction(text));
            return true;
        }

        return false;
    }
}
