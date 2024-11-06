package com.javarush.telegram;

import com.javarush.telegram.command.SendTextMessage;
import com.javarush.telegram.command.UpdateTextMessage;
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
            String prompt = TelegramBotFileUtil.loadPrompt("gpt");
            Long chatId = getChatId(update);
            Message message = new SendTextMessage("Please wait.").handle(bot, chatId);

            String messageText = update.getMessage().getText();
            String answer = context().chatGPTService().sendMessage(prompt, messageText);
            new UpdateTextMessage(message, answer).handle(bot, chatId);

            return true;
        }
        return false;
    }
}
