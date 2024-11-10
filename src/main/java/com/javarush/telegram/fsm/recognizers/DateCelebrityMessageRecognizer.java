package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import com.javarush.telegram.fsm.instructions.DateCelebrityMessageInstruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogMode.DATE;

@Immutable
public final class DateCelebrityMessageRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        if (context.getMode() == DATE) {
            var text = contentOf(update);
            fsmOutput.addInstruction(new DateCelebrityMessageInstruction(text));
            return true;
        }

        return false;
    }
}
