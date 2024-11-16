package com.javarush.telegram.fsm;

/**
 * Represents a returned result by finite state machine after running.
 */
public enum FiniteStateMachineResult {
    DEADLOCK,
    FINISHED,
    NOT_STARTED
}
