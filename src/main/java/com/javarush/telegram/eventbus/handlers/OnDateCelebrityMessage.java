package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.ChatGPTMessageEvent;
import com.javarush.telegram.eventbus.events.DateCelebrityMessageEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.eventbus.events.UpdatedTextMessageEvent;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

@Immutable
public final class OnDateCelebrityMessage
        implements EventHandler<DateCelebrityMessageEvent>, Subscribable {

    private static final String PLEASE_WAIT = "Please wait!";

    @Override
    @Subscribe
    public void handle(DateCelebrityMessageEvent event) {
        event.payload().ifPresent(
                (gptRequest) ->
                        new TextMessageEvent(
                                new TextMessage(PLEASE_WAIT),
                                (message) ->
                                        new ChatGPTMessageEvent(
                                                gptRequest,
                                                (gptAnswer) ->
                                                        new UpdatedTextMessageEvent(
                                                                new UpdatedTextMessage(message, gptAnswer)
                                                        ).post()
                                        ).post()
                        ).post()
        );
    }
}
