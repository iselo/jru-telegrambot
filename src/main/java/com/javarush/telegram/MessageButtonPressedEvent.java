package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class MessageButtonPressedEvent extends AbstractCallbackQuery {

    public MessageButtonPressedEvent(TelegramBotContext context) {
        super(context);
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {

        if (context().getMode() == DialogMode.MESSAGE) {
            String data = update.getCallbackQuery().getData();
            if (data.startsWith("message_")) {
                String prompt = TelegramBotFileUtil.loadPrompt(data);

                String history = String.join("\n\n", context().chatHistory());
                String answer = context().chatGPTService().sendMessage(prompt, history);
                sendTextMessage(bot, update, answer);

                context().chatHistory().clear();
                return true;
            }
        }
        return false;
    }
}
