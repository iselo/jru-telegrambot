package com.javarush.telegram.fsm.instructions;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.DialogMode;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class DialogModeInstruction extends Instruction {

    private final DialogMode mode;

    public DialogModeInstruction(DialogMode mode) {
        this.mode = checkNotNull(mode);
    }

    @Override
    protected void execute(Responder responder, TelegramBotContext context) {
        context.setMode(mode);
    }
}
