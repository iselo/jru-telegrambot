package com.javarush.telegram;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.handlers.EventHandler;
import com.javarush.telegram.eventbus.handlers.OnAskQuestion;
import com.javarush.telegram.eventbus.handlers.OnBotMenu;
import com.javarush.telegram.eventbus.handlers.OnChatDialog;
import com.javarush.telegram.eventbus.handlers.OnChatMessageSend;
import com.javarush.telegram.eventbus.handlers.OnDateCelebrityMessage;
import com.javarush.telegram.eventbus.handlers.OnDateCelebritySelect;
import com.javarush.telegram.eventbus.handlers.OnDateDialog;
import com.javarush.telegram.eventbus.handlers.OnGptDialog;
import com.javarush.telegram.eventbus.handlers.OnGptMessage;
import com.javarush.telegram.eventbus.handlers.OnLastQuestion;
import com.javarush.telegram.eventbus.handlers.OnOpenerDialog;
import com.javarush.telegram.eventbus.handlers.OnOpenerQuestion;
import com.javarush.telegram.eventbus.handlers.OnProfileDialog;
import com.javarush.telegram.eventbus.handlers.OnProfileQuestion;
import com.javarush.telegram.survey.UserInfoSurvey;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
@SuppressWarnings("Immutable")
public final class TelegramBotContext {

    private final ChatGPTService chatGPTService;
    private final ChatHistory chatHistory = new ChatHistory();
    private final UserInfoSurvey survey = new UserInfoSurvey();
    private final DialogMode dialogMode = new DialogMode();
    private final ImmutableList<EventHandler<?>> eventHandlers =
            ImmutableList.of(
                    new OnBotMenu(),
                    new OnGptDialog(),
                    new OnGptMessage(),
                    new OnChatDialog(),
                    new OnChatMessageSend(),
                    new OnDateDialog(),
                    new OnDateCelebritySelect(),
                    new OnDateCelebrityMessage(),
                    new OnOpenerDialog(),
                    new OnOpenerQuestion(),
                    new OnProfileDialog(),
                    new OnProfileQuestion(),
                    new OnAskQuestion(),
                    new OnLastQuestion()
            );

    public TelegramBotContext(ChatGPTService chatGPTService) {
        this.chatGPTService = checkNotNull(chatGPTService);
        configure();
    }


    /**
     * Returns survey history.
     */
    public UserInfoSurvey survey() {
        return survey;
    }

    /**
     * Returns dialog mode.
     */
    public DialogMode dialogMode() {
        return dialogMode;
    }

    private void configure() {
        var eventBus = Service.INSTANCE.eventBus();
        eventBus.register(dialogMode);
        eventBus.register(chatGPTService);
        eventBus.register(chatHistory);
        eventBus.register(survey);
        eventBus.register(survey.questions());
        eventHandlers.forEach(eventBus::register);
    }
}
