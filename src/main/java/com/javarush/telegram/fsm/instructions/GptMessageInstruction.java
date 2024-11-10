package com.javarush.telegram.fsm.instructions;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class GptMessageInstruction extends Instruction {

    private static final String PLEASE_WAIT = "Please wait!";
    private static final String GPT = "gpt";

    private final String text;

    public GptMessageInstruction(String text) {
        this.text = checkNotNull(text);
    }

    @Override
    protected void execute(Responder responder, TelegramBotContext context) {
        var message = responder.execute(new TextMessage(PLEASE_WAIT));
        var prompt = TelegramBotFileUtil.loadPrompt(GPT);
        var answer = context.chatGPTService().sendMessage(prompt, text);
        responder.execute(new UpdatedTextMessage(message, answer));
    }
}
