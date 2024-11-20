package com.javarush.telegram.eventbus;

import com.google.errorprone.annotations.Immutable;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable(containerOf = "T")
public final class Payload<T> {

    private final T data;

    private Payload() {
        this.data = null;
    }

    private Payload(T data) {
        this.data = checkNotNull(data);
    }

    public static <T> Payload<T> of(T data) {
        return new Payload<>(data);
    }

    public static <T> Payload<T> ofEmpty() {
        return new Payload<>();
    }

    public T data() {
        return this.data;
    }
}
