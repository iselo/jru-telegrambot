package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.AskQuestionEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.responder.TextMessage;

@Immutable
public final class OnAskQuestion implements EventHandler<AskQuestionEvent>, Subscribable {

    @Override
    @Subscribe
    public void handle(AskQuestionEvent event) {
        event.payload().ifPresent(
                (questionValue) -> new TextMessageEvent(new TextMessage(questionValue)).post()
        );

    }
}
