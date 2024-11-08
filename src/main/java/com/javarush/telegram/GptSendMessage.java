package com.javarush.telegram;

import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;
import javax.annotation.concurrent.Immutable;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Immutable
public final class GptSendMessage extends AbstractMessage {

    public GptSendMessage(TelegramBotContext context) {
        super(context);
    }

    /**
     * @inheritDoc
     */
    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {

        if (context().getMode() == DialogMode.GPT) {
            Responder responder = new Responder(bot, getChatId(update));

            Message message = responder.execute(new TextMessage("Please wait."));

            String prompt = TelegramBotFileUtil.loadPrompt("gpt");
            String messageText = update.getMessage().getText();
            String answer = context().chatGPTService().sendMessage(prompt, messageText);

            responder.execute(new UpdatedTextMessage(message, answer));

            return true;
        }
        return false;
    }
}
