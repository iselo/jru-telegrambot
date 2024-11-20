package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.events.LastQuestionEvent;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

@Immutable
public final class OnLastQuestion implements EventHandler<LastQuestionEvent> {

    private static final String PLEASE_WAIT = "Please wait!";

    @Override
    @Subscribe
    public void handle(LastQuestionEvent event) {
        var responder = event.responder();
        var context = event.context();

        var message = responder.execute(new TextMessage(PLEASE_WAIT));

        var prompt = TelegramBotFileUtil.loadPrompt(event.toString());
        var userInfo = context.survey().newUserInfo();
        var gptAnswer = context.chatGPTService().sendMessage(prompt, userInfo.toString());

        responder.execute(new UpdatedTextMessage(message, gptAnswer));
    }
}
