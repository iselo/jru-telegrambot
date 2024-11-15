package com.javarush.telegram.fsm.instructions;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public abstract class Instruction {

    public final void apply(Responder responder, TelegramBotContext context) {
        checkNotNull(responder);
        checkNotNull(context);
        execute(responder, context);
    }

    /**
     * Executes this instruction by a given {@code Responder} at given {@code TelegramBotContext}.
     *
     * @param responder the instance of a Responder
     * @param context   the Telegram bot context
     */
    protected abstract void execute(Responder responder, TelegramBotContext context);
}
