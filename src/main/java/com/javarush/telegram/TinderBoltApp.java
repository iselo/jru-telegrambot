package com.javarush.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

public final class TinderBoltApp extends MultiSessionTelegramBot {

    private final List<ResponseMessage> events = new ArrayList<>();

    public TinderBoltApp() {
        super(System.getenv("TELEGRAM_BOT_NAME"), System.getenv("TELEGRAM_BOT_TOKEN"));
        configure();
    }

    private void configure() {
        String token = System.getenv("OPEN_AI_TOKEN");
        TelegramBotContext context = new TelegramBotContext(ChatGPTService.of(token));

        this.events.addAll(
                List.of(
                        new StartDialogEvent(context),
                        new OpenerDialogEvent(context),
                        new ProfileDialogEvent(context),
                        new MessageDialogEvent(context),
                        new DateDialogEvent(context),
                        new GptDialogEvent(context),
                        new ProfileQuestionEvent(context),
                        new MessageNextEvent(context),
                        new MessageButtonPressedEvent(context),
                        new CelebritySelectedEvent(context),
                        new CelebritySendMessageEvent(context),
                        new GptSendMessageEvent(context)
                )
        );
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        for (ResponseMessage message : events) {
            if (message.apply(this, update)) {
                break;
            }
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TinderBoltApp());
    }
}
