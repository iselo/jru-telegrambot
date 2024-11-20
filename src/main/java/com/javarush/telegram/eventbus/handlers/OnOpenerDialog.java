package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.events.OpenerDialogEvent;
import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.TextMessage;

@Immutable
public final class OnOpenerDialog implements EventHandler<OpenerDialogEvent> {

    private static final String OPENER = "opener";

    @Override
    @Subscribe
    public void handle(OpenerDialogEvent event) {
        var text = TelegramBotFileUtil.loadMessage(OPENER);
        var responder = event.responder();
        responder.execute(new PhotoMessage(OPENER));
        responder.execute(new TextMessage(text));
    }
}
