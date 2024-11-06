package com.javarush.telegram;

import com.javarush.telegram.command.SendPhotoMessage;
import com.javarush.telegram.command.SendTextButtonsMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;
import java.util.Map;

import static com.javarush.telegram.DialogMode.MESSAGE;

@Immutable
public final class MessageDialog extends AbstractMessage {

    private static final String KEYWORD = "message";

    private final Map<String, String> buttons = Map.of(
            "Наступне повідомлення", "message_next",
            "Запросити на побачення", "message_date"
    );

    public MessageDialog(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(MESSAGE.toString())) {
            context().setMode(MESSAGE);
            context().chatHistory().clear();

            Long chatId = getChatId(update);
            new SendPhotoMessage(KEYWORD).handle(bot, chatId);

            String text = TelegramBotFileUtil.loadMessage(KEYWORD);
            new SendTextButtonsMessage(text, buttons).handle(bot, chatId);

            return true;
        }

        return false;
    }
}
