package com.javarush.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

public class TinderBoltApp extends MultiSessionTelegramBot {
    public static final String TELEGRAM_BOT_NAME = "bot-name"; //TODO: додай ім'я бота в лапках
    public static final String TELEGRAM_BOT_TOKEN = "bot-token"; //TODO: додай токен бота в лапках
    public static final String OPEN_AI_TOKEN = "chat-gpt-token"; //TODO: додай токен ChatGPT у лапках
    public DialogMode mode = DialogMode.MAIN;
    private List<String> chat;
    private UserInfo myInfo;
    private UserInfo personInfo;
    private int questionNumber;

    public TinderBoltApp() {
        super(TELEGRAM_BOT_NAME, TELEGRAM_BOT_TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        //TODO: основний функціонал бота будемо писати тут
        String message = getMessageText();

        switch (message) {
            case "/start" -> {
                showMainMenu(
                        "menu item 1", "/start",
                        "menu item 2", "/profile",
                        "menu item 3", "/gpt"
                        // ...
                );

                sendPhotoMessage("main");
                String menu = loadMessage("main");
                sendTextMessage(menu);
                return;
            }
            case "/gpt" -> {
                mode = DialogMode.GPT;
                //..
                return;
            }
        }

        switch (mode) {
            case GPT -> {

            }
            case DATE -> {
                String query = getCallbackQueryButtonKey();

                if (query.startsWith("date_")) {
                    // ...
                    return;
                }
            }
        }
    }

    private void askQuestion(String message) {
        switch (questionNumber) {
            case 1 -> {
                myInfo.name = message;
                questionNumber =2;
                sendTextMessage("Next question:");
                return;
            }
            case 2 -> {

            }
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TinderBoltApp());
    }
}
