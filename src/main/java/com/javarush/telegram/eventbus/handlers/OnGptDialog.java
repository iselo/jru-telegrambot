package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.GptDialogEvent;
import com.javarush.telegram.eventbus.events.PhotoMessageEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.TextMessage;

@Immutable
public final class OnGptDialog implements EventHandler<GptDialogEvent>, Subscribable {

    private static final String GPT = "gpt";

    @Override
    @Subscribe
    public void handle(GptDialogEvent event) {
        var text = TelegramBotFileUtil.loadMessage(GPT);
        new PhotoMessageEvent(new PhotoMessage(GPT)).post();
        new TextMessageEvent(new TextMessage(text)).post();
    }
}
