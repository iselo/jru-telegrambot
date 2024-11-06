package com.javarush.telegram;

import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

import static com.javarush.telegram.DialogMode.OPENER;

@Immutable
public final class OpenerDialog extends AbstractMessage {

    private static final String KEYWORD = "opener";

    public OpenerDialog(TelegramBotContext context) {
        super(context);
    }

    /**
     * @inheritDoc
     */
    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(OPENER.toString())) {
            context().setMode(OPENER);

            Responder responder = new Responder(bot, getChatId(update));

            responder.execute(new PhotoMessage(KEYWORD));

            String text = TelegramBotFileUtil.loadMessage(KEYWORD);
            responder.execute(new TextMessage(text));
        }

        return false;
    }
}
