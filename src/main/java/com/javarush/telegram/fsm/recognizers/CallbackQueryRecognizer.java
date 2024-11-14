package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public abstract class CallbackQueryRecognizer extends Recognizer {

    @Override
    public final boolean accept(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        checkNotNull(update);
        return update.hasCallbackQuery() && super.accept(update, context, fsmOutput);
    }

    @Override
    protected final String contentOf(Update update) {
        return update.getCallbackQuery().getData();
    }
}
