package com.javarush.telegram.fsm;

import com.google.errorprone.annotations.Immutable;

/**
 * Functional interface of finite state machine output instruction.
 */
@Immutable
public interface Instruction {

    void execute();
}
