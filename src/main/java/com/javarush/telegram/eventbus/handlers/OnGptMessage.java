package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.events.GptMessageSendEvent;
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
        var responder = event.responder();
        var message = responder.execute(new TextMessage(PLEASE_WAIT));
        var prompt = TelegramBotFileUtil.loadPrompt(GPT);
        var answer = event.context().chatGPTService().sendMessage(prompt, event.text());
        responder.execute(new UpdatedTextMessage(message, answer));
    }

}
