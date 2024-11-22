package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Payload;
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
        new TextMessageEvent(
                Payload.of(new TextMessage(PLEASE_WAIT)),
                (message) -> {

                    new ChatGPTMessageEvent(
                            Payload.of(event.payload().value()),
                            (gptAnswer) -> {
                                new UpdatedTextMessageEvent(
                                        Payload.of(new UpdatedTextMessage(message, gptAnswer))
                                ).post();
                            }
                    ).post();
                }
        ).post();
    }
}
