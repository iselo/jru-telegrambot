package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.ChatGPTMessageEvent;
import com.javarush.telegram.eventbus.events.ChatGPTPromptEvent;
import com.javarush.telegram.eventbus.events.ChatHistoryEvent;
import com.javarush.telegram.eventbus.events.ChatMessageSendEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.eventbus.events.UpdatedTextMessageEvent;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

@Immutable
public final class OnChatMessageSend implements EventHandler<ChatMessageSendEvent>, Subscribable {

    private static final String PLEASE_WAIT = "Please wait!";

    @Override
    @Subscribe
    @SuppressWarnings("FutureReturnValueIgnored")
    public void handle(ChatMessageSendEvent event) {
        var keyword = event.getPayload();
        TextMessageEvent.builder()
                .payload(new TextMessage(PLEASE_WAIT))
                .consumer((message) -> {
                            var prompt = TelegramBotFileUtil.loadPrompt(keyword);
                            new ChatGPTPromptEvent(prompt).post();
                            new ChatHistoryEvent(
                                    (history) ->
                                            ChatGPTMessageEvent.builder()
                                                    .payload(history)
                                                    .consumer((gptAnswer) -> new UpdatedTextMessageEvent(
                                                                    new UpdatedTextMessage(message, gptAnswer)
                                                            ).post()
                                                    )
                                                    .build()
                                                    .post()
                            ).post();
                        }
                )
                .build()
                .post();
    }
}
