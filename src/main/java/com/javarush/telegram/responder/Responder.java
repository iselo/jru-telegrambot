package com.javarush.telegram.responder;

import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.MultiSessionTelegramBot;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.MenuEvent;
import com.javarush.telegram.eventbus.events.PhotoMessageEvent;
import com.javarush.telegram.eventbus.events.TextButtonsMessageEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.eventbus.events.UpdatedTextMessageEvent;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class Responder implements Subscribable {

    @SuppressWarnings("Immutable")
    private final MultiSessionTelegramBot bot;
    private final Long chatId;

    public Responder(MultiSessionTelegramBot bot, Long chatId) {
        this.bot = checkNotNull(bot);
        this.chatId = checkNotNull(chatId);
    }

    @Subscribe
    void handle(PhotoMessageEvent event) {
        execute(event.getPayload());
    }

    @Subscribe
    void handle(TextMessageEvent event) {
        var textMessage = event.getPayload();
        var result = this.execute(textMessage);
        Optional.ofNullable(event.getConsumer())
                .ifPresent(consumer -> consumer.accept(result));
    }

    @Subscribe
    void handle(UpdatedTextMessageEvent event) {
        execute(event.getPayload());
    }

    @Subscribe
    void handle(TextButtonsMessageEvent event) {
        execute(event.getPayload());
    }

    @Subscribe
    void handle(MenuEvent event) {
        execute(event.getPayload());
    }

    @CanIgnoreReturnValue
    private Message execute(TextMessage command) {
        checkNotNull(command);
        return command.handle(bot, chatId);
    }

    @CanIgnoreReturnValue
    private Message execute(PhotoMessage command) {
        checkNotNull(command);
        return command.handle(bot, chatId);
    }

    @CanIgnoreReturnValue
    private CompletableFuture<Serializable> execute(UpdatedTextMessage command) {
        checkNotNull(command);
        return command.handle(bot, chatId);
    }

    @CanIgnoreReturnValue
    private Boolean execute(Menu command) {
        checkNotNull(command);
        return command.handle(bot, chatId);
    }
}
