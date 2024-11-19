package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.events.DateCelebritySelectEvent;
import com.javarush.telegram.responder.PhotoMessage;

@Immutable
public final class OnDateCelebritySelect implements EventHandler<DateCelebritySelectEvent> {

    @Override
    @Subscribe
    public void handle(DateCelebritySelectEvent event) {
        var data = event.toString();
        var prompt = TelegramBotFileUtil.loadPrompt(data);
        event.context().chatGPTService().setPrompt(prompt);
        event.responder().execute(new PhotoMessage(data));

    }
}
