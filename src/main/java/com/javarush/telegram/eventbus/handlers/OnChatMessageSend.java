package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.events.ChatMessageSendEvent;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

@Immutable
public final class OnChatMessageSend implements EventHandler<ChatMessageSendEvent> {

    @Override
    @Subscribe
    @SuppressWarnings("FutureReturnValueIgnored")
    public void handle(ChatMessageSendEvent event) {
        var responder = event.responder();
        var message = responder.execute(new TextMessage("Please wait!"));

        var prompt = TelegramBotFileUtil.loadPrompt(event.toString());
        var history = event.context().chatHistory().toString();
        var gptAnswer = event.context().chatGPTService().sendMessage(prompt, history);

        responder.execute(new UpdatedTextMessage(message, gptAnswer));
    }
}
