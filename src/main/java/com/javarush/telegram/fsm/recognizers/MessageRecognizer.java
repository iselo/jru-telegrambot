package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.fsm.Chronology;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public abstract class MessageRecognizer extends Recognizer {

    @Override
    public boolean accept(Update update,
                          TelegramBotContext context,
                          Chronology chronology) {
        return update.hasMessage() && super.accept(update, context, chronology);
    }

    @Override
    protected String contentOf(Update update) {
        return update.getMessage().getText();
    }
}
