package com.javarush.telegram.fsm.recognizers;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.FiniteStateMachineFactory;
import com.javarush.telegram.fsm.FiniteStateMachineResult;
import com.javarush.telegram.fsm.FsmOutput;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public final class BotModeRecogniser extends CallbackQueryOrMessageRecognizer {

    @Override
    protected boolean handle(Update update, BotReadOnlyContext context, FsmOutput fsmOutput) {
        var fsmResult =
                FiniteStateMachineFactory.BOT_MODE
                        .newInstance()
                        .run(update, context, fsmOutput);

        return fsmResult == FiniteStateMachineResult.FINISHED && fsmOutput.isPresent();
    }
}
