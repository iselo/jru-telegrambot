package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;
import java.util.Map;

import static com.javarush.telegram.DialogMode.MESSAGE;

@Immutable
public final class MessageDialogEvent extends AbstractMessage {

    private static final String KEYWORD = "message";

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

        if (messageText.equalsIgnoreCase(MESSAGE.toString())) {
            context().setMode(MESSAGE);
            context().chatHistory().clear();

            sendPhotoMessage(bot, update, KEYWORD);
            String text = TelegramBotFileUtil.loadMessage(KEYWORD);
            sendTextButtonsMessage(bot, update, text, buttons);

            return true;
        }

        return false;
    }
}
