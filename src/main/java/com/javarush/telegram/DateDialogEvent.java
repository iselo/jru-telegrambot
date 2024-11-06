package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;
import java.util.Map;

import static com.javarush.telegram.DialogMode.DATE;

@Immutable
public final class DateDialogEvent extends AbstractMessage {

    private final Map<String, String> buttons = Map.of(
            "Аріана Гранде \uD83D\uDD25", "date_grande",
            "Марго Роббі \uD83D\uDD25\uD83D\uDD25", "date_robbie",
            "Зендея \uD83D\uDD25\uD83D\uDD25\uD83D\uDD25", "date_zendaya",
            "Райан Гослінг \uD83D\uDE0E", "date_gosling",
            "Том Харді \uD83D\uDE0E\uD83D\uDE0E", "date_hardy"
    );

    public DateDialogEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(DATE.toString())) {
            context().setMode(DATE);

            String text = TelegramBotFileUtil.loadMessage("date");
            sendPhotoMessage(bot, update, "date");
            sendTextButtonsMessage(bot, update, text, buttons);

            return true;
        }
        return false;
    }
}
