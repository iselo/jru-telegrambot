package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.events.DateDialogEvent;
import com.javarush.telegram.fsm.Chronology;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogModeState.DATE;

@Immutable
public final class DateDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology) {
        if (contentOf(update).equalsIgnoreCase(DATE.toString())) {
            chronology.add(() -> new DateDialogEvent(Payload.ofEmpty()).post());
            return true;
        }

        return false;
    }
}
