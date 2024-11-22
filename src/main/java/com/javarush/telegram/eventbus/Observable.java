package com.javarush.telegram.eventbus;

public interface Observable extends Broadcastable {

    default void post() {
        Bus.instance().post(this);
    }
}
