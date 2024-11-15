package com.javarush.telegram;

import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import javax.annotation.concurrent.Immutable;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public final class CelebritySendMessage extends AbstractMessage {

    public CelebritySendMessage(TelegramBotContext context) {
        super(context);
    }

    /**
     * @inheritDoc
     */
    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {

        if (context().getMode() == DialogMode.DATE) {
            String messageText = update.getMessage().getText();
            String answer = context().chatGPTService().addMessage(messageText);
            new Responder(bot, getChatId(update))
                    .execute(new TextMessage(answer));
            return true;
        }
        return false;
    }
}
