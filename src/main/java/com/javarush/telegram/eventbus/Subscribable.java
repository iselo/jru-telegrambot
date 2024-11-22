package com.javarush.telegram.eventbus;

public interface Subscribable extends Broadcastable {

    default void register() {
        Bus.instance().register(this);
    }

    default void unregister() {
        Bus.instance().unregister(this);
    }
}
