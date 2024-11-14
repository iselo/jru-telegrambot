package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public abstract class CallbackQueryOrMessageRecognizer extends Recognizer {

    @Override
    public final boolean accept(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        return (update.hasCallbackQuery() || update.hasMessage())
                && super.accept(update, context, fsmOutput);
    }

    @Override
    protected String contentOf(Update update) {
        return null;
    }
}
