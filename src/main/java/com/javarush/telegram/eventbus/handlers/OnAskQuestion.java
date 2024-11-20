package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.events.AskQuestionEvent;
import com.javarush.telegram.responder.TextMessage;

@Immutable
public final class OnAskQuestion implements EventHandler<AskQuestionEvent> {

    @Override
    @Subscribe
    public void handle(AskQuestionEvent event) {
        var responder = event.responder();
        var questionValue = event.toString();
        responder.execute(new TextMessage(questionValue));
    }
}
