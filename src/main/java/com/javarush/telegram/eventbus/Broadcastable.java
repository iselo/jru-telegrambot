package com.javarush.telegram.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * Abstract interface introduced for the inheritance that adds functionality of operating with
 * event bus.
 */
interface Broadcastable {

    /**
     * The event bus.
     */
    final class Bus {

        private static final EventBus INSTANCE = new EventBus();

        private Bus() {
        }

        /**
         * Returns a singleton instance.
         */
        public static EventBus instance() {
            return INSTANCE;
        }
    }
}
