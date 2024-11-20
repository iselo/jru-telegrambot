package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.eventbus.events.ChatGPTMessageEvent;
import com.javarush.telegram.eventbus.events.ChatGPTPromptEvent;
import com.javarush.telegram.eventbus.events.LastQuestionEvent;
import com.javarush.telegram.eventbus.events.SurveyEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.eventbus.events.UpdatedTextMessageEvent;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

@Immutable
public final class OnLastQuestion implements EventHandler<LastQuestionEvent> {

    private static final String PLEASE_WAIT = "Please wait!";

    @Override
    @Subscribe
    public void handle(LastQuestionEvent event) {

        new TextMessageEvent(
                Payload.of(new TextMessage(PLEASE_WAIT)),
                (message) -> {
                    var prompt = TelegramBotFileUtil.loadPrompt(event.payload().data());
                    new SurveyEvent(
                            Payload.ofEmpty(),
                            (survey) -> {
                                var userInfo = survey.newUserInfo();

                                new ChatGPTPromptEvent(Payload.of(prompt)).post();
                                new ChatGPTMessageEvent(
                                        Payload.of(userInfo.toString()),
                                        (gptAnswer) -> {
                                            new UpdatedTextMessageEvent(
                                                    Payload.of(new UpdatedTextMessage(message, gptAnswer))
                                            ).post();
                                        }
                                ).post();
                            }
                    ).post();
                }
        ).post();
    }
}
