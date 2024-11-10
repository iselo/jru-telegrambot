package com.javarush.telegram.fsm.instructions;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

@Immutable
public final class QuestionsResetInstruction extends Instruction {

    @Override
    protected void execute(Responder responder, TelegramBotContext context) {
        context.survey().questions().resetQuestions();
    }
}
