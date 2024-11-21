package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.events.GptMessageSendEvent;
import com.javarush.telegram.fsm.Chronology;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogModeState.GPT;

@Immutable
public final class GptMessageRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology) {
        if (context.dialogMode().state() == GPT) {
            var text = contentOf(update);
            chronology.add(() -> new GptMessageSendEvent(Payload.of(text)).post());
            return true;
        }

        return false;
    }
}
