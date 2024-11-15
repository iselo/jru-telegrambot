package com.javarush.telegram.fsm.instructions;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class ChatMessageAddInstruction extends Instruction {

    private final String textMessage;

    public ChatMessageAddInstruction(String textMessage) {
        this.textMessage = checkNotNull(textMessage);
    }

    @Override
    protected void execute(Responder responder, TelegramBotContext context) {
        context.chatHistory().add(textMessage);
    }
}
