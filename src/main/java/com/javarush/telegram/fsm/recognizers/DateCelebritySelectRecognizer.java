package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.events.DateCelebritySelectEvent;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.Instruction;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.javarush.telegram.DialogModeState.DATE;

@Immutable
public final class DateCelebritySelectRecognizer extends CallbackQueryRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology) {
        var data = contentOf(update);

        if (context.dialogMode().state() == DATE && data.startsWith("date_")) {
            chronology.add(new Instruction() {
                @Override
                protected void execute(TelegramBotContext context) {
                    new DateCelebritySelectEvent(Payload.of(data)).post();
                }
            });
            return true;
        }

        return false;
    }
}
