package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.responder.Responder;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public abstract class Recognizer {

    /**
     * Recognises the state of Finite State Machine for Telegram bot update.
     *
     * @param update    the current Telegram update
     * @param context   the Telegram bot context
     * @param chronology the output of finite state machine run
     * @return {@code true} if state of the current update was recognised, otherwise {@code false}
     */
    public boolean accept(Update update,
                          TelegramBotContext context,
                          Chronology chronology,
                          Responder responder) {
        checkNotNull(update);
        checkNotNull(context);
        checkNotNull(chronology);
        return handle(update, context, chronology, responder);
    }

    /**
     * Handles a response message.
     *
     * @param update the Telegram bot chat Update instance
     * @return {@code true} if handling is completed, otherwise {@code false}
     */
    protected abstract boolean handle(Update update,
                                      TelegramBotContext context,
                                      Chronology fsmOutput,
                                      Responder responder);

    /**
     * Returns a message content or callbackquery content of given Update.
     *
     * @param update the Telegram bot chat Update instance
     * @return content string
     */
    protected abstract String contentOf(Update update);
}
