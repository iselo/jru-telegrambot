package com.javarush.telegram.fsm.instructions;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.Responder;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class DateCelebritySelectInstruction extends Instruction {

    private final String data;

    public DateCelebritySelectInstruction(String data) {
        this.data = checkNotNull(data);
    }

    @Override
    protected void execute(Responder responder, TelegramBotContext context) {
        var prompt = TelegramBotFileUtil.loadPrompt(data);
        context.chatGPTService().setPrompt(prompt);
        responder.execute(new PhotoMessage(data));
    }
}
