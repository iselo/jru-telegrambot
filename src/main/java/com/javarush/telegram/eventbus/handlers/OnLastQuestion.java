package com.javarush.telegram.eventbus.handlers;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.ChatGPTMessageEvent;
import com.javarush.telegram.eventbus.events.ChatGPTPromptEvent;
import com.javarush.telegram.eventbus.events.LastQuestionEvent;
import com.javarush.telegram.eventbus.events.SurveyEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.eventbus.events.UpdatedTextMessageEvent;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;

@Immutable
public final class OnLastQuestion implements EventHandler<LastQuestionEvent>, Subscribable {

    private static final String PLEASE_WAIT = "Please wait!";

    @Override
    @Subscribe
    public void handle(LastQuestionEvent event) {
        event.payload().ifPresent(
                (keyword) ->
                        new TextMessageEvent(
                                new TextMessage(PLEASE_WAIT),
                                (message) ->
                                        new SurveyEvent(
                                                null,
                                                (survey) -> {
                                                    var userInfo = survey.newUserInfo();
                                                    var prompt = TelegramBotFileUtil.loadPrompt(keyword);
                                                    new ChatGPTPromptEvent(prompt).post();
                                                    new ChatGPTMessageEvent(
                                                            userInfo.toString(),
                                                            (gptAnswer) -> new UpdatedTextMessageEvent(
                                                                    new UpdatedTextMessage(message, gptAnswer)
                                                            ).post()
                                                    ).post();
                                                }
                                        ).post()
                        ).post()
        );
    }
}
