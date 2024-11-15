package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FsmOutput;
import com.javarush.telegram.fsm.instructions.ChatMessageSendInstruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogMode.CHAT;

@Immutable
public final class ChatMessageSendRecognizer extends CallbackQueryRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        var data = contentOf(update);

        if (context.getMode() == CHAT && data.startsWith("chat_")) {
            fsmOutput.addInstruction(new ChatMessageSendInstruction(data));
            return true;
        }

        return false;
    }
}
