package com.javarush.telegram.eventbus;

import com.google.errorprone.annotations.Immutable;

import java.util.Optional;

/**
 * An event of a given type that describes its payload. Any event has {@code Observable}
 * functionality.
 *
 * @param <P> the payload type
 */
@Immutable(containerOf = "P")
public abstract class Event<P> implements Observable {

    private final Optional<P> payload;

    protected Event(P payload) {
        this.payload = Optional.ofNullable(payload);
    }

    /**
     * Returns event payload.
     */
    public final Optional<P> payload() {
        return payload;
    }
}
