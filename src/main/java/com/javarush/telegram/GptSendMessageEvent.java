package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class GptSendMessageEvent extends AbstractMessage {

    public GptSendMessageEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {

        if (context().getMode() == DialogMode.GPT) {
            String prompt = TelegramBotFileUtil.loadPrompt("gpt");
            Message message = sendTextMessage(bot, update, "Please wait.");

            String messageText = update.getMessage().getText();
            String answer = context().chatGPTService().sendMessage(prompt, messageText);
            updateTextMessage(bot, update, message, answer);

            return true;
        }
        return false;
    }
}
