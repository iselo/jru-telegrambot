package com.javarush.telegram.responder;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

public interface MessageResponder {

    Message execute(TextMessage command);

    Message execute(PhotoMessage command);

    CompletableFuture<Serializable> execute(UpdatedTextMessage command);

    Boolean execute(ChatMenu command);
}
