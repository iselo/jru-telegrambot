package com.javarush.telegram;

import com.javarush.telegram.fsm.Chronology;
import com.javarush.telegram.fsm.FiniteStateMachineFactory;
import com.javarush.telegram.fsm.FiniteStateMachineResult;
import com.javarush.telegram.responder.Responder;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * The Telegram bot application.
 */
public final class TinderBotApp extends MultiSessionTelegramBot {

    private static final String TELEGRAM_BOT_NAME = System.getenv("TELEGRAM_BOT_NAME");
    private static final String TELEGRAM_BOT_TOKEN = System.getenv("TELEGRAM_BOT_TOKEN");
    private static final String OPEN_AI_TOKEN = System.getenv("OPEN_AI_TOKEN");

    private final TelegramBotContext context = new TelegramBotContext(ChatGPTService.of(OPEN_AI_TOKEN));

    private TinderBotApp() {
        super(TELEGRAM_BOT_NAME, TELEGRAM_BOT_TOKEN);
    }

    /**
     * Application entry point.
     */
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TinderBotApp());
    }

    @Override
    protected void onUpdateEventReceived(Update update) {
        Chronology chronology = new Chronology();
        Responder responder = new Responder(this, chatId(update));

        FiniteStateMachineResult fsmResult =
                FiniteStateMachineFactory.MAIN
                        .newInstance()
                        .run(update, context, chronology, responder);

        if (fsmResult == FiniteStateMachineResult.FINISHED) {

            chronology.queue()
                    .forEach(instruction -> instruction.apply(responder, context));
        }
    }
}
