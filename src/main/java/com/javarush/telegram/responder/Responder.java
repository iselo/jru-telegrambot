package com.javarush.telegram.responder;

import com.javarush.telegram.MultiSessionTelegramBot;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Immutable
public final class Responder implements MessageResponder {

    private final MultiSessionTelegramBot bot;

    private final Long chatId;

    public Responder(MultiSessionTelegramBot bot, Long chatId) {
        this.bot = bot;
        this.chatId = chatId;
    }

    @Override
    public Message execute(TextMessage command) {
        return command.handle(bot, chatId);
    }

    @Override
    public Message execute(PhotoMessage command) {
        return command.handle(bot, chatId);
    }

    @Override
    public CompletableFuture<Serializable> execute(UpdatedTextMessage command) {
        return command.handle(bot, chatId);
    }

    @Override
    public Boolean execute(ChatMenu command) {
        return command.handle(bot, chatId);
    }


}
