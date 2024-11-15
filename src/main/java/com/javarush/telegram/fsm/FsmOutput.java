package com.javarush.telegram.fsm;

import com.google.common.collect.ImmutableList;
import com.javarush.telegram.fsm.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents an output of finite state machines run.
 * <p>
 * It contains instruction list to be performed as a response on Telegram bot {@code Update} event.
 */
public final class FsmOutput {

    private final List<Instruction> instructions = new ArrayList<>();

    public void addInstruction(Instruction instruction) {
        checkNotNull(instruction);
        instructions.add(instruction);
    }

    /**
     * Returns a list of instructions.
     */
    public ImmutableList<Instruction> instructions() {
        return ImmutableList.copyOf(instructions);
    }

    /**
     * Returns {@code true} if output is not empty, otherwise {@code false}.
     */
    public boolean isPresent() {
        return !instructions.isEmpty();
    }
}
