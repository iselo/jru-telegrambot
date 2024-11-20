package com.javarush.telegram.fsm;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public abstract class Instruction {

    public final void apply(TelegramBotContext context) {
        checkNotNull(context);
        execute(context);
    }

    /**
     * Executes this instruction at given {@code TelegramBotContext}.
     *
     * @param context the Telegram bot context
     */
    protected abstract void execute(TelegramBotContext context);
}
