package com.javarush.telegram.eventbus;

import com.google.errorprone.annotations.Immutable;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * An event that returns value from subscriber to consumer.
 *
 * @param <P> the payload type
 * @param <C> the consumer argument type
 */
@Immutable(containerOf = "P")
public abstract class EventWithReturn<P, C> extends Event<P> {

    @SuppressWarnings("Immutable")
    private final Optional<Consumer<C>> consumer;

    protected EventWithReturn(P payload) {
        super(payload);
        this.consumer = Optional.empty();
    }

    protected EventWithReturn(P payload, Consumer<C> consumer) {
        super(payload);
        this.consumer = Optional.of(consumer);
    }

    /**
     * Returns value from event subscriber.
     * <p>
     * The given argument will be passed to the optional consumer to perform operation.
     */
    public void returnToConsumer(C argument) {
        consumer.ifPresent(consumer -> consumer.accept(argument));
    }
}
