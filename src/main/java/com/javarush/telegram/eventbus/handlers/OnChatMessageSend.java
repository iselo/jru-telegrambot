package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.events.ChatGPTMessageEvent;
import com.javarush.telegram.eventbus.events.ChatGPTPromptEvent;
import com.javarush.telegram.eventbus.events.ChatHistoryEvent;
import com.javarush.telegram.eventbus.events.ChatMessageSendEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.eventbus.events.UpdatedTextMessageEvent;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

@Immutable
public final class OnChatMessageSend implements EventHandler<ChatMessageSendEvent> {

    private static final String PLEASE_WAIT = "Please wait!";

    @Override
    @Subscribe
    @SuppressWarnings("FutureReturnValueIgnored")
    public void handle(ChatMessageSendEvent event) {
        new TextMessageEvent(
                Payload.of(new TextMessage(PLEASE_WAIT)),
                (message) -> {
                    var prompt = TelegramBotFileUtil.loadPrompt(event.payload().data());
                    new ChatGPTPromptEvent(Payload.of(prompt)).post();

                    new ChatHistoryEvent(
                            Payload.ofEmpty(),
                            (history) -> {
                                new ChatGPTMessageEvent(
                                        Payload.of(history),
                                        (gptAnswer) -> {
                                            new UpdatedTextMessageEvent(
                                                    Payload.of(new UpdatedTextMessage(message, gptAnswer))
                                            ).post();
                                        }
                                ).post();
                            }
                    ).post();


                }).post();
    }
}
