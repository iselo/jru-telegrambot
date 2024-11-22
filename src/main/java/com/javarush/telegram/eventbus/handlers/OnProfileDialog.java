package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.PhotoMessageEvent;
import com.javarush.telegram.eventbus.events.ProfileDialogEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.TextMessage;

@Immutable
public final class OnProfileDialog implements EventHandler<ProfileDialogEvent>, Subscribable {

    private static final String PROFILE = "profile";

    @Override
    @Subscribe
    public void handle(ProfileDialogEvent event) {
        var text = TelegramBotFileUtil.loadMessage(PROFILE);
        new PhotoMessageEvent(new PhotoMessage(PROFILE)).post();
        new TextMessageEvent(new TextMessage(text)).post();
    }
}
