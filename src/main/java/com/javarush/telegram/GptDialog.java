package com.javarush.telegram;

import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

import static com.javarush.telegram.DialogMode.GPT;

@Immutable
public final class GptDialog extends AbstractMessage {

    private static final String KEYWORD = "gpt";

    public GptDialog(TelegramBotContext context) {
        super(context);
    }

    /**
     * @inheritDoc
     */
    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(GPT.toString())) {
            context().setMode(GPT);

            Responder responder = new Responder(bot, getChatId(update));

            responder.accept(new PhotoMessage(KEYWORD));

            String text = TelegramBotFileUtil.loadMessage(KEYWORD);
            responder.accept(new TextMessage(text));

            return true;
        }

        return false;
    }
}
