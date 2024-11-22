package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.ChatGPTMessageEvent;
import com.javarush.telegram.eventbus.events.ChatGPTPromptEvent;
import com.javarush.telegram.eventbus.events.GptMessageSendEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.eventbus.events.UpdatedTextMessageEvent;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

@Immutable
public final class OnGptMessage implements EventHandler<GptMessageSendEvent>, Subscribable {

    private static final String PLEASE_WAIT = "Please wait!";
    private static final String GPT = "gpt";

    @Override
    @Subscribe
    @SuppressWarnings("FutureReturnValueIgnored")
    public void handle(GptMessageSendEvent event) {
        event.payload().ifPresent(
                (gptRequest) ->
                        new TextMessageEvent(
                                new TextMessage(PLEASE_WAIT),
                                (message) -> {
                                    var prompt = TelegramBotFileUtil.loadPrompt(GPT);
                                    new ChatGPTPromptEvent(prompt).post();
                                    new ChatGPTMessageEvent(
                                            gptRequest,
                                            (gptAnswer) ->
                                                    new UpdatedTextMessageEvent(
                                                            new UpdatedTextMessage(message, gptAnswer)
                                                    ).post()
                                    ).post();
                                }
                        ).post()
        );
    }
}
