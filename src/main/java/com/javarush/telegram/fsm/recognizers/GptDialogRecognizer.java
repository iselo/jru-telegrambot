package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.events.GptDialogEvent;
import com.javarush.telegram.fsm.Chronology;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogModeState.GPT;

@Immutable
public final class GptDialogRecognizer implements MessageRecognizer {

    @Override
    public boolean handle(Update update, TelegramBotContext context, Chronology chronology) {
        if (contentOf(update).equalsIgnoreCase(GPT.toString())) {
            chronology.add(() -> new GptDialogEvent().post());
            return true;
        }
        return false;
    }
}
