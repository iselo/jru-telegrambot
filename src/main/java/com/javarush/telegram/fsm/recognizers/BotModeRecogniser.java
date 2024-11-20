package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.FiniteStateMachineFactory;
import com.javarush.telegram.fsm.FiniteStateMachineResult;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public final class BotModeRecogniser extends CallbackQueryOrMessageRecognizer {

    @Override
    protected boolean handle(Update update,
                             TelegramBotContext context,
                             Chronology chronology) {
        var fsmResult =
                FiniteStateMachineFactory.BOT_MODE
                        .newInstance()
                        .run(update, context, chronology);

        return fsmResult == FiniteStateMachineResult.FINISHED && chronology.isPresent();
    }
}
