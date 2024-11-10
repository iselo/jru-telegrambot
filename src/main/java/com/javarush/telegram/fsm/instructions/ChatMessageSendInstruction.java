package com.javarush.telegram.fsm.instructions;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class ChatMessageSendInstruction extends Instruction {

    private final String data;

    public ChatMessageSendInstruction(String data) {
        this.data = checkNotNull(data);
    }

    @Override
    protected void execute(Responder responder, TelegramBotContext context) {
        var message = responder.execute(new TextMessage("Please wait!"));

        var prompt = TelegramBotFileUtil.loadPrompt(data);
        var history = String.join("\n\n", context.chatHistory());
        var gptAnswer = context.chatGPTService().sendMessage(prompt, history);

        responder.execute(new UpdatedTextMessage(message, gptAnswer));
    }
}
