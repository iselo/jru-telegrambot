package com.javarush.telegram;

import static com.javarush.telegram.DialogMode.PROFILE;

import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import javax.annotation.concurrent.Immutable;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public final class ProfileDialog extends AbstractMessage {

    private static final String KEYWORD = "profile";

    public ProfileDialog(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(PROFILE.toString())) {
            context().setMode(PROFILE);

            Responder responder = new Responder(bot, getChatId(update));

            responder.execute(new PhotoMessage(KEYWORD));

            String text = TelegramBotFileUtil.loadMessage(KEYWORD);
            responder.execute(new TextMessage(text));
        }

        return false;
    }
}
