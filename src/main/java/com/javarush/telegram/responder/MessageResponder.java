package com.javarush.telegram.responder;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageResponder {

    Message execute(TextMessage command);

    Message execute(PhotoMessage command);

    CompletableFuture<Serializable> execute(UpdatedTextMessage command);

    Boolean execute(ChatMenu command);
}
