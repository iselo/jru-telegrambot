package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.eventbus.events.DialogModeChangeEvent;
import com.javarush.telegram.eventbus.events.ProfileDialogEvent;
import com.javarush.telegram.eventbus.events.ProfileQuestionEvent;
import com.javarush.telegram.eventbus.events.SurveyQuestionsResetEvent;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.Instruction;
import com.javarush.telegram.responder.Responder;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.javarush.telegram.DialogModeState.PROFILE;

@Immutable
public final class ProfileDialogRecognizer extends MessageRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology,
                             Responder responder) {
        if (contentOf(update).equalsIgnoreCase(PROFILE.toString())) {
            chronology.add(new Instruction() {
                @Override
                protected void execute(Responder responder, TelegramBotContext context) {
                    var eventBus = context.eventBus();
                    eventBus.post(new DialogModeChangeEvent(PROFILE));
                    eventBus.post(new ProfileDialogEvent(responder, context));
                    eventBus.post(new SurveyQuestionsResetEvent());
                    var noPreviousAnswer = Optional.<String>empty();
                    eventBus.post(new ProfileQuestionEvent(responder, context, noPreviousAnswer));
                }
            });

            return true;
        }

        return false;
    }
}
