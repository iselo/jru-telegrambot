package com.javarush.telegram.eventbus;

import com.google.errorprone.annotations.Immutable;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable(containerOf = "P")
public abstract class Event<P extends Payload<?>> implements Observable {

    private final P payload;

    protected Event(P payload) {
        this.payload = checkNotNull(payload);
    }

    public final P payload() {
        return payload;
    }

}
