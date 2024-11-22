package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.ChatGPTPromptEvent;
import com.javarush.telegram.eventbus.events.DateCelebritySelectEvent;
import com.javarush.telegram.eventbus.events.PhotoMessageEvent;
import com.javarush.telegram.responder.PhotoMessage;

@Immutable
public final class OnDateCelebritySelect
        implements EventHandler<DateCelebritySelectEvent>, Subscribable {

    @Override
    @Subscribe
    public void handle(DateCelebritySelectEvent event) {
        var payload = event.payload();
        var data = payload.value();
        var prompt = TelegramBotFileUtil.loadPrompt(data);
        new ChatGPTPromptEvent(Payload.of(prompt)).post();
        new PhotoMessageEvent(Payload.of(new PhotoMessage(data))).post();
    }
}
