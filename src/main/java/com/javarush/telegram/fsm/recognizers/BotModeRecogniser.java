package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.FiniteStateMachine;
import com.javarush.telegram.fsm.FiniteStateMachineFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public final class BotModeRecogniser implements CallbackQueryOrMessageRecognizer {

    @Override
    public boolean handle(Update update, TelegramBotContext context, Chronology chronology) {
        var fsmResult = FiniteStateMachineFactory.BOT_MODE.newInstance().run(update, context, chronology);

        return fsmResult == FiniteStateMachine.Result.FINISHED && chronology.isPresent();
    }
}
