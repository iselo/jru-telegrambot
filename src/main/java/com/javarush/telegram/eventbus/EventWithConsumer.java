package com.javarush.telegram.eventbus;

import com.google.errorprone.annotations.Immutable;

import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable(containerOf = "P")
public abstract class EventWithConsumer<P, C>
        extends Event<P>{

    @SuppressWarnings("Immutable")
    private final Consumer<C> consumer;

    protected EventWithConsumer(P payload) {
        super(payload);
        this.consumer = null;
    }

    protected EventWithConsumer(P payload, Consumer<C> consumer) {
        super(payload);
        this.consumer = checkNotNull(consumer);
    }

    public Consumer<C> consumer() {
        return consumer;
    }

}
