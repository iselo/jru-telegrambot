package com.javarush.telegram.responder;

import com.google.errorprone.annotations.Immutable;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Immutable
interface MessageResponder {

    Message execute(TextMessage command);

    Message execute(PhotoMessage command);

    CompletableFuture<Serializable> execute(UpdatedTextMessage command);

    Boolean execute(Menu command);
}
