package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.events.ProfileDialogEvent;
import com.javarush.telegram.eventbus.events.ProfileQuestionEvent;
import com.javarush.telegram.fsm.Chronology;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.javarush.telegram.DialogModeState.PROFILE;

@Immutable
public final class ProfileDialogRecognizer implements MessageRecognizer {

    @Override
    public boolean handle(Update update, TelegramBotContext context, Chronology chronology) {
        if (contentOf(update).equalsIgnoreCase(PROFILE.toString())) {
            chronology.add(() -> {
                new ProfileDialogEvent(Payload.empty()).post();
                var noPreviousAnswer = Optional.<String>empty();
                new ProfileQuestionEvent(Payload.of(noPreviousAnswer)).post();
            });
            return true;
        }
        return false;
    }
}
