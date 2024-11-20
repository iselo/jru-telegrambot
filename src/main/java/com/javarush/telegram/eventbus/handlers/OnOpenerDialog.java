package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.events.OpenerDialogEvent;
import com.javarush.telegram.eventbus.events.PhotoMessageEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.TextMessage;

@Immutable
public final class OnOpenerDialog implements EventHandler<OpenerDialogEvent> {

    private static final String OPENER = "opener";

    @Override
    @Subscribe
    public void handle(OpenerDialogEvent event) {
        var text = TelegramBotFileUtil.loadMessage(OPENER);
        new PhotoMessageEvent(Payload.of(new PhotoMessage(OPENER))).post();
        new TextMessageEvent(Payload.of(new TextMessage(text))).post();
    }
}
