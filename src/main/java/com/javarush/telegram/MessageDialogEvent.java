package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;
import java.util.Map;

@Immutable
public final class MessageDialogEvent extends AbstractMessage {

    private final static String EVENT_KEYWORD = "message";
    private final static String EVENT = "/" + EVENT_KEYWORD;

    private final Map<String, String> buttons = Map.of(
            "Наступне повідомлення", "message_next",
            "Запросити на побачення", "message_date"
    );

    public MessageDialogEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(EVENT)) {
            context().setMode(DialogMode.MESSAGE);
            context().chatHistory().clear();

            sendPhotoMessage(bot, update, EVENT_KEYWORD);
            String text = TelegramBotFileUtil.loadMessage(EVENT_KEYWORD);
            sendTextButtonsMessage(bot, update, text, buttons);

            return true;
        }

        return false;
    }
}
