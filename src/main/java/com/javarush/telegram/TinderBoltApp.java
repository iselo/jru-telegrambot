package com.javarush.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.List;

@Immutable
public final class TinderBoltApp extends MultiSessionTelegramBot {

    private final List<ResponseMessage> responseMessages = new ArrayList<>();

    public TinderBoltApp() {
        super(System.getenv("TELEGRAM_BOT_NAME"), System.getenv("TELEGRAM_BOT_TOKEN"));
        configure();
    }

    private void configure() {
        String token = System.getenv("OPEN_AI_TOKEN");
        TelegramBotContext context = new TelegramBotContext(ChatGPTService.of(token));

        this.responseMessages.addAll(
                List.of(
                        new StartDialog(context),
                        new OpenerDialog(context),
                        new ProfileDialog(context),
                        new MessageDialog(context),
                        new DateDialog(context),
                        new GptDialog(context),
                        new ProfileQuestion(context),
                        new MessageNext(context),
                        new MessageButtonPressed(context),
                        new CelebritySelected(context),
                        new CelebritySendMessage(context),
                        new GptSendMessage(context)
                )
        );
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        for (ResponseMessage message : responseMessages) {
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
