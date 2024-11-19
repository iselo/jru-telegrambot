package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.responder.Responder;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public abstract class CallbackQueryRecognizer extends Recognizer {

    @Override
    public final boolean accept(Update update,
                                TelegramBotContext context,
                                Chronology chronology,
                                Responder responder) {
        checkNotNull(update);
        return update.hasCallbackQuery() && super.accept(update, context, chronology, responder);
    }

    @Override
    protected final String contentOf(Update update) {
        return update.getCallbackQuery().getData();
    }
}
