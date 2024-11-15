package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import com.javarush.telegram.fsm.instructions.DateCelebritySelectInstruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogMode.DATE;

@Immutable
public final class DateCelebritySelectRecognizer extends CallbackQueryRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        var data = contentOf(update);

        if (context.getMode() == DATE && data.startsWith("date_")) {
            fsmOutput.addInstruction(new DateCelebritySelectInstruction(data));
            return true;
        }

        return false;
    }
}
