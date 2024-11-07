package com.javarush.telegram.responder;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

public interface MessageResponder {

    Message accept(TextMessage command);

    Message accept(PhotoMessage command);

    CompletableFuture<Serializable> accept(UpdatedTextMessage command);

    Boolean accept(ChatMenu command);
}
