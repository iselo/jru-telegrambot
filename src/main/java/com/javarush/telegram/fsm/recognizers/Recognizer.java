package com.javarush.telegram.fsm.recognizers;

import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class Recognizer {

    /**
     * Recognises the state of Finite State Machine for Telegram bot update.
     *
     * @param update    the current Telegram update
     * @param context   the Telegram bot context
     * @param fsmOutput the output of finite state machine run
     * @return {@code true} if state of the current update was recognised, otherwise {@code false}
     */
    public boolean accept(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        checkNotNull(update);
        checkNotNull(context);
        checkNotNull(fsmOutput);
        return handle(update, context, fsmOutput);
    }

    /**
     * Handles a response message.
     *
     * @param update the Telegram bot chat Update instance
     * @return {@code true} if handling is completed, otherwise {@code false}
     */
    protected abstract boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput);

    /**
     * Returns a message content or callbackquery content of given Update.
     *
     * @param update the Telegram bot chat Update instance
     * @return content string
     */
    protected abstract String contentOf(Update update);
}
