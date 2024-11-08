package com.javarush.telegram;

import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.Responder;
import javax.annotation.concurrent.Immutable;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public final class CelebritySelected extends AbstractCallbackQuery {

    public CelebritySelected(TelegramBotContext context) {
        super(context);
    }

    /**
     * @inheritDoc
     */
    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {

        if (context().getMode() == DialogMode.DATE) {
            String data = update.getCallbackQuery().getData();

            if (data.startsWith("date_")) {
                new Responder(bot, getChatId(update))
                        .execute(new PhotoMessage(data));

                String prompt = TelegramBotFileUtil.loadPrompt(data);
                context().chatGPTService().setPrompt(prompt);
                return true;
            }
        }
        return false;
    }
}
