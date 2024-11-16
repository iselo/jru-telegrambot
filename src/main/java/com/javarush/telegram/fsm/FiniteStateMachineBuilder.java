package com.javarush.telegram.fsm;

import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.fsm.recognizers.Recognizer;

/**
 * Represents a finite state machine builder of given type.
 *
 * @param <E> the type of finite state machine
 */
@Immutable(containerOf = "E")
interface FiniteStateMachineBuilder<E extends Enum> {

    /**
     * Sets start state at which a finite state machine starts its running.
     */
    FiniteStateMachineBuilder<E> setStartState(E startState);

    /**
     * Sets finish state at which a finite state machine finishes its running.
     */
    FiniteStateMachineBuilder<E> setFinishState(E finishState);

    /**
     * Adds a set of possible states to which this state can transition.
     */
    FiniteStateMachineBuilder<E> addTransition(E fsmState, ImmutableSet<E> fsmStateSet);


    /**
     * Adds recogniser of a given state.
     */
    FiniteStateMachineBuilder<E> addRecogniser(E fsmState, Recognizer recognizer);

    /**
     * Builds the finite state machine instance.
     */
    FiniteStateMachine<E> build();
}
