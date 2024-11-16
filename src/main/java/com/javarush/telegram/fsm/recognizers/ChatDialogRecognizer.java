package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import com.javarush.telegram.fsm.instructions.ChatDialogInstruction;
import com.javarush.telegram.fsm.instructions.ChatHistoryClearInstruction;
import com.javarush.telegram.fsm.instructions.DialogModeInstruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogMode.CHAT;

@Immutable
public final class ChatDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        if (contentOf(update).equalsIgnoreCase(CHAT.toString())) {
            fsmOutput.addInstruction(new DialogModeInstruction(CHAT));
            fsmOutput.addInstruction(new ChatHistoryClearInstruction());
            fsmOutput.addInstruction(new ChatDialogInstruction());

            return true;
        }

        return false;
    }
}
