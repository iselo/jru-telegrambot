package com.javarush.telegram.responder;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.MultiSessionTelegramBot;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class Responder implements MessageResponder {

    @SuppressWarnings("Immutable")
    private final MultiSessionTelegramBot bot;
    private final Long chatId;

    public Responder(MultiSessionTelegramBot bot, Long chatId) {
        this.bot = checkNotNull(bot);
        this.chatId = checkNotNull(chatId);
    }

    @Override
    @CanIgnoreReturnValue
    public Message execute(TextMessage command) {
        checkNotNull(command);
        return command.handle(bot, chatId);
    }

    @Override
    @CanIgnoreReturnValue
    public Message execute(PhotoMessage command) {
        checkNotNull(command);
        return command.handle(bot, chatId);
    }

    @Override
    @CanIgnoreReturnValue
    public CompletableFuture<Serializable> execute(UpdatedTextMessage command) {
        checkNotNull(command);
        return command.handle(bot, chatId);
    }

    @Override
    @CanIgnoreReturnValue
    public Boolean execute(Menu command) {
        checkNotNull(command);
        return command.handle(bot, chatId);
    }
}
