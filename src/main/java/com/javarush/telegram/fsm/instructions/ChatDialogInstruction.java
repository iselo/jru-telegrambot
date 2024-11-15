package com.javarush.telegram.fsm.instructions;

import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextButtonsMessage;

@Immutable
public final class ChatDialogInstruction extends Instruction {

    private static final String CHAT = "chat";

    private final ImmutableMap<String, String> buttons = ImmutableMap.of(
            "Наступне повідомлення", "chat_next",
            "Запросити на побачення", "chat_date"
    );

    @Override
    protected void execute(Responder responder, TelegramBotContext context) {
        var messageText = TelegramBotFileUtil.loadMessage(CHAT);
        responder.execute(new PhotoMessage(CHAT));
        responder.execute(new TextButtonsMessage(messageText, buttons));
    }
}
