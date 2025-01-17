package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.events.ProfileQuestionEvent;
import com.javarush.telegram.fsm.Chronology;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.javarush.telegram.DialogModeState.PROFILE;

@Immutable
public final class ProfileQuestionRecognizer implements MessageRecognizer {

    @Override
    public boolean handle(Update update, TelegramBotContext context, Chronology chronology) {
        if (context.dialogMode().state() == PROFILE && context.survey().questions().isPresent()) {
            var text = contentOf(update);
            var previousAnswer = Optional.of(text);
            chronology.add(() -> new ProfileQuestionEvent(previousAnswer).post());
            return true;
        }
        return false;
    }
}
