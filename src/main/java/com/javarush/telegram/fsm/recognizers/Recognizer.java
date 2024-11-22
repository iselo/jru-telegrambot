package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.fsm.Chronology;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public interface Recognizer {

    /**
     * Handles a response message.
     *
     * @param update the Telegram bot chat Update instance
     * @return {@code true} if handling is completed, otherwise {@code false}
     */
    boolean handle(Update update, TelegramBotContext context, Chronology fsmOutput);

    /**
     * Recognises the state of Finite State Machine for Telegram bot update.
     *
     * @param update     the current Telegram update
     * @param context    the Telegram bot context
     * @param chronology the output of finite state machine run
     * @return {@code true} if state of the current update was recognised, otherwise {@code false}
     */
    default boolean accept(Update update, TelegramBotContext context, Chronology chronology) {
        checkNotNull(update);
        checkNotNull(context);
        checkNotNull(chronology);
        return handle(update, context, chronology);
    }
}
