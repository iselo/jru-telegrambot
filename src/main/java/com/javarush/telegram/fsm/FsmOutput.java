package com.javarush.telegram.fsm;

import com.javarush.telegram.fsm.instructions.Instruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class FsmOutput {

    private final List<Instruction> instructions = new ArrayList<>();

    public FsmOutput addInstruction(Instruction instruction) {
        checkNotNull(instruction);
        instructions.add(instruction);
        return this;
    }

    public List<Instruction> instructions() {
        return Collections.unmodifiableList(instructions);
    }

    public boolean isPresent() {
        return !instructions.isEmpty();
    }
}
