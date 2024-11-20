package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.events.ChatGPTMessageEvent;
import com.javarush.telegram.eventbus.events.ChatGPTPromptEvent;
import com.javarush.telegram.eventbus.events.GptMessageSendEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.eventbus.events.UpdatedTextMessageEvent;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

@Immutable
public final class OnGptMessage implements EventHandler<GptMessageSendEvent> {

    private static final String PLEASE_WAIT = "Please wait!";
    private static final String GPT = "gpt";

    @Override
    @Subscribe
    @SuppressWarnings("FutureReturnValueIgnored")
    public void handle(GptMessageSendEvent event) {

        new TextMessageEvent(
                Payload.of(new TextMessage(PLEASE_WAIT)),
                (message) -> {
                    var prompt = TelegramBotFileUtil.loadPrompt(GPT);

                    new ChatGPTPromptEvent(Payload.of(prompt)).post();
                    new ChatGPTMessageEvent(
                            Payload.of(event.payload().data()),
                            (gptAnswer) -> {
                                new UpdatedTextMessageEvent(
                                        Payload.of(new UpdatedTextMessage(message, gptAnswer))
                                ).post();
                            }
                    ).post();


                }
        ).post();
    }
}
