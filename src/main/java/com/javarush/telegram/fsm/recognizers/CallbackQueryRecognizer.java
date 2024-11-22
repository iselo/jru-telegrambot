package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.fsm.Chronology;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
interface CallbackQueryRecognizer extends Recognizer {

    @Override
    default boolean accept(Update update, TelegramBotContext context, Chronology chronology) {
        return update.hasCallbackQuery() && Recognizer.super.accept(update, context, chronology);
    }

    default String contentOf(Update update) {
        return update.getCallbackQuery().getData();
    }
}
