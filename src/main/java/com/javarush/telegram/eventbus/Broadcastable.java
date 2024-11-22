package com.javarush.telegram.eventbus;

import com.google.common.eventbus.EventBus;

interface Broadcastable {

    final class Bus {

        private static final EventBus INSTANCE = new EventBus();

        private Bus() {
        }

        public static EventBus instance() {
            return INSTANCE;
        }
    }
}
