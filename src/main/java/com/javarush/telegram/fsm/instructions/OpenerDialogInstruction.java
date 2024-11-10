package com.javarush.telegram.fsm.instructions;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;

@Immutable
public final class OpenerDialogInstruction extends Instruction {

    private static final String OPENER = "opener";

    @Override
    protected void execute(Responder responder, TelegramBotContext context) {
        var text = TelegramBotFileUtil.loadMessage(OPENER);
        responder.execute(new PhotoMessage(OPENER));
        responder.execute(new TextMessage(text));
    }
}
