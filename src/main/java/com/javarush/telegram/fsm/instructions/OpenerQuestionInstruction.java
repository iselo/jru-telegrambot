package com.javarush.telegram.fsm.instructions;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

import java.util.Optional;

@Immutable
public final class OpenerQuestionInstruction extends Instruction {

    private static final String PLEASE_WAIT = "Please wait!";
    private static final String OPENER = "opener";

    private final Optional<String> previousAnswer;

    public OpenerQuestionInstruction(Optional<String> previousAnswer) {
        this.previousAnswer = previousAnswer;
    }

    @Override
    @SuppressWarnings("FutureReturnValueIgnored")
    protected void execute(Responder responder, TelegramBotContext context) {
        var survey = context.survey();
        var question = survey.questions().nextQuestion();

        previousAnswer.ifPresent(answer -> question.accept(survey, answer));

        var maybeQuestionValue = question.value();
        maybeQuestionValue.ifPresentOrElse(
                questionValue -> responder.execute(new TextMessage(questionValue)),
                () -> {
                    var message = responder.execute(new TextMessage(PLEASE_WAIT));

                    var prompt = TelegramBotFileUtil.loadPrompt(OPENER);
                    var personInfo = survey.newUserInfo();
                    var gptAnswer = context.chatGPTService().sendMessage(prompt, personInfo.toString());

                    responder.execute(new UpdatedTextMessage(message, gptAnswer));
                }
        );
    }
}
