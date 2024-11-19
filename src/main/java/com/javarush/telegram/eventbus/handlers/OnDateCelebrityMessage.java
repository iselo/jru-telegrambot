package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.events.DateCelebrityMessageEvent;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

@Immutable
public final class OnDateCelebrityMessage implements EventHandler<DateCelebrityMessageEvent> {

    private static final String PLEASE_WAIT = "Please wait!";

    @Override
    @Subscribe
    public void handle(DateCelebrityMessageEvent event) {
        var responder = event.responder();
        var message = responder.execute(new TextMessage(PLEASE_WAIT));
        var gptAnswer = event.context().chatGPTService().addMessage(event.toString());
        responder.execute(new UpdatedTextMessage(message, gptAnswer));

    }
}
