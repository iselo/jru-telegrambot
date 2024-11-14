package com.javarush.telegram.fsm.instructions;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class DateCelebrityMessageInstruction extends Instruction {

    private static final String PLEASE_WAIT = "Please wait!";

    private final String text;

    public DateCelebrityMessageInstruction(String text) {
        this.text = checkNotNull(text);
    }

    @Override
    @SuppressWarnings("FutureReturnValueIgnored")
    protected void execute(Responder responder, TelegramBotContext context) {
        var message = responder.execute(new TextMessage(PLEASE_WAIT));
        var gptAnswer = context.chatGPTService().addMessage(text);
        responder.execute(new UpdatedTextMessage(message, gptAnswer));
    }
}
