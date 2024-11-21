package com.javarush.telegram.eventbus;

import com.google.errorprone.annotations.Immutable;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable(containerOf = "T")
public final class Payload<T> {

    private static final Payload<?> EMPTY = new Payload<>();

    private final T value;

    private Payload() {
        this.value = null;
    }

    private Payload(T value) {
        this.value = checkNotNull(value);
    }

    /**
     * Returns a Payload instance with value.
     */
    public static <T> Payload<T> of(T value) {
        return new Payload<T>(value);
    }

    /**
     * Returns an empty Payload instance. No value is present for this Payload.
     */
    public static <T> Payload<T> empty() {
        @SuppressWarnings("unchecked")
        Payload<T> empty = (Payload<T>) EMPTY;
        return empty;
    }

    public T value() {
        return value;
    }
}
