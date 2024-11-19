package com.javarush.telegram.eventbus.handlers;

import com.google.common.collect.ImmutableMap;
import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.events.ChatDialogEvent;
import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.TextButtonsMessage;

@Immutable
public final class OnChatDialog implements EventHandler<ChatDialogEvent> {

    private static final String CHAT = "chat";

    private final ImmutableMap<String, String> buttons = ImmutableMap.of(
            "Наступне повідомлення", "chat_next",
            "Запросити на побачення", "chat_date"
    );

    @Override
    @Subscribe
    public void handle(ChatDialogEvent event) {
        var messageText = TelegramBotFileUtil.loadMessage(CHAT);
        var responder = event.responder();
        responder.execute(new PhotoMessage(CHAT));
        responder.execute(new TextButtonsMessage(messageText, buttons));
    }
}
