package com.javarush.telegram.fsm;

import com.google.errorprone.annotations.Immutable;

@Immutable
public interface Instruction {

    void execute();
}
