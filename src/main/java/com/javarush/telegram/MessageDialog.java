package com.javarush.telegram;

import static com.javarush.telegram.DialogMode.MESSAGE;

import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextButtonsMessage;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
import org.telegram.telegrambots.meta.api.objects.Update;

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

    /**
     * @inheritDoc
     */
    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(MESSAGE.toString())) {
            context().setMode(MESSAGE);
            context().chatHistory().clear();

            Responder responder = new Responder(bot, getChatId(update));

            responder.execute(new PhotoMessage(KEYWORD));

            String text = TelegramBotFileUtil.loadMessage(KEYWORD);
            responder.execute(new TextButtonsMessage(text, buttons));

            return true;
        }

        return false;
    }
}
