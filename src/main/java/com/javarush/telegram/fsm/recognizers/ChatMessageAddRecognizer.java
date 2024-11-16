package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import com.javarush.telegram.fsm.instructions.ChatMessageAddInstruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogMode.CHAT;

@Immutable
public final class ChatMessageAddRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        if (context.getMode() == CHAT) {
            var messageText = contentOf(update);
            fsmOutput.addInstruction(new ChatMessageAddInstruction(messageText));
            return true;
        }

        return false;
    }
}
