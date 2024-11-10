package com.javarush.telegram.fsm.recognizers;

import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class MessageRecognizer extends Recognizer {

    @Override
    public boolean accept(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        return update.hasMessage() && super.accept(update, context, fsmOutput);
    }

    @Override
    protected String contentOf(Update update) {
        return update.getMessage().getText();
    }
}
