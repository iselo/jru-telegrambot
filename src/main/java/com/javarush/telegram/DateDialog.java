package com.javarush.telegram;

import static com.javarush.telegram.DialogMode.DATE;

import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextButtonsMessage;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public final class DateDialog extends AbstractMessage {

    private static final String KEYWORD = "date";

    private final Map<String, String> buttons = Map.of(
            "Аріана Гранде \uD83D\uDD25", "date_grande",
            "Марго Роббі \uD83D\uDD25\uD83D\uDD25", "date_robbie",
            "Зендея \uD83D\uDD25\uD83D\uDD25\uD83D\uDD25", "date_zendaya",
            "Райан Гослінг \uD83D\uDE0E", "date_gosling",
            "Том Харді \uD83D\uDE0E\uD83D\uDE0E", "date_hardy"
    );

    public DateDialog(TelegramBotContext context) {
        super(context);
    }


    /**
     * @inheritDoc
     */
    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(DATE.toString())) {
            context().setMode(DATE);

            Responder responder = new Responder(bot, getChatId(update));

            responder.execute(new PhotoMessage(KEYWORD));

            String text = TelegramBotFileUtil.loadMessage(KEYWORD);
            responder.execute(new TextButtonsMessage(text, buttons));

            return true;
        }
        return false;
    }
}
