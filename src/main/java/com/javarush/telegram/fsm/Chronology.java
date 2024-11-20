package com.javarush.telegram.fsm;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents an output of finite state machines run.
 * <p>
 * It contains instruction list to be performed as a response on Telegram bot {@code Update} event.
 */
public final class Chronology {

    private final List<Instruction> events = new ArrayList<>();

    public void add(Instruction event) {
        checkNotNull(event);
        events.add(event);
    }

    /**
     * Returns a list of instructions.
     */
    public ImmutableList<Instruction> queue() {
        return ImmutableList.copyOf(events);
    }

    /**
     * Returns {@code true} if output is not empty, otherwise {@code false}.
     */
    public boolean isPresent() {
        return !events.isEmpty();
    }
}
