package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.FiniteStateMachineFactory;
import com.javarush.telegram.fsm.FiniteStateMachineResult;
import com.javarush.telegram.responder.Responder;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public final class BotMenuRecogniser extends CallbackQueryOrMessageRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology,
                             Responder responder) {
        var fsmResult =
                FiniteStateMachineFactory.BOT_MENU
                        .newInstance()
                        .run(update, context, chronology, responder);

        return fsmResult == FiniteStateMachineResult.FINISHED && chronology.isPresent();
    }
}
