package com.javarush.telegram.eventbus;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.Service;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable(containerOf = "P")
public abstract class Event<P extends Payload<?>> {

    private final P payload;

    protected Event(P payload) {
        this.payload = checkNotNull(payload);
    }

    public final P payload() {
        return payload;
    }

    public final void post(){
        Service.INSTANCE.eventBus().post(this);
    }
}
