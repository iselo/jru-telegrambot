package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.events.OpenerQuestionEvent;
import com.javarush.telegram.fsm.Chronology;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.javarush.telegram.DialogModeState.OPENER;

@Immutable
public final class OpenerQuestionRecognizer implements MessageRecognizer {

    @Override
    public boolean handle(Update update, TelegramBotContext context, Chronology chronology) {
        if (context.dialogMode().state() == OPENER && context.survey().questions().isPresent()) {
            var text = contentOf(update);
            var previousAnswer = Optional.of(text);
            chronology.add(() -> new OpenerQuestionEvent(previousAnswer).post());
            return true;
        }
        return false;
    }
}
