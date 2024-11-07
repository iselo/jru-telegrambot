package com.javarush.telegram;

import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import com.javarush.telegram.responder.UpdatedTextMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class GptSendMessage extends AbstractMessage {

    public GptSendMessage(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {

        if (context().getMode() == DialogMode.GPT) {
            Responder responder = new Responder(bot, getChatId(update));

            Message message = responder.accept(new TextMessage("Please wait."));

            String prompt = TelegramBotFileUtil.loadPrompt("gpt");
            String messageText = update.getMessage().getText();
            String answer = context().chatGPTService().sendMessage(prompt, messageText);

            responder.accept(new UpdatedTextMessage(message, answer));

            return true;
        }
        return false;
    }
}
