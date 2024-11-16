package com.javarush.telegram.fsm;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.BotReadOnlyContext;
import com.javarush.telegram.fsm.recognizers.Recognizer;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A Finite State Machine of a given type.
 *
 * @param <E> the type of the Finite State Machine
 */
@Immutable(containerOf = "E")
public final class FiniteStateMachine<E extends Enum> {

    private final E startState;
    private final E finishState;
    private final ImmutableMap<E, ImmutableSet<E>> transitionTable;
    private final ImmutableMap<E, Recognizer> recognizers;

    public FiniteStateMachine(E startState,
                              E finishState,
                              ImmutableMap<E, ImmutableSet<E>> transitionTable,
                              ImmutableMap<E, Recognizer> recognizers) {
        this.startState = checkNotNull(startState);
        this.finishState = checkNotNull(finishState);
        this.transitionTable = transitionTable;
        this.recognizers = recognizers;
    }

    /**
     * Returns a new builder of {@code FiniteStateMachine} instance.
     *
     * @param <E> type of {@code FiniteStateMachine}
     * @return a new instance of the builder
     */
    public static <E extends Enum> FiniteStateMachineBuilder<E> newBuilder() {
        return new Builder<>();
    }

    /**
     * Runs finite state machine to recognize states.
     *
     * @param update  the current Telegram update
     * @param context the Telegram bot context
     * @return result of the finite state machine run
     */
    public FiniteStateMachineResult run(Update update,
                                        BotReadOnlyContext context,
                                        FsmOutput fsmOutput) {
        E currentFsmState = startState;

        while (currentFsmState != finishState) {
            Set<E> transitions = transitions(currentFsmState);

            Optional<E> nextFsmState = moveForward(transitions, update, context, fsmOutput);

            if (nextFsmState.isEmpty()) {

                return currentFsmState == startState ?
                        FiniteStateMachineResult.NOT_STARTED :
                        FiniteStateMachineResult.DEADLOCK;
            }

            currentFsmState = nextFsmState.get();
        }
        return FiniteStateMachineResult.FINISHED;
    }

    private Set<E> transitions(E fsmState) {
        return transitionTable.get(fsmState);
    }

    private Recognizer recognizer(E fsmState) {
        return recognizers.get(fsmState);
    }

    private Optional<E> moveForward(Iterable<E> transitions,
                                    Update update,
                                    BotReadOnlyContext context,
                                    FsmOutput fsmOutput) {
        for (E fsmState : transitions) {

            var recognizer = recognizer(fsmState);

            if (recognizer.accept(update, context, fsmOutput)) {
                return Optional.of(fsmState);
            }
        }
        return Optional.empty();
    }

    /**
     * A builder of finite state machine of a given type.
     *
     * @param <E> the builder type
     */
    @SuppressWarnings("Immutable")
    private static final class Builder<E extends Enum> implements FiniteStateMachineBuilder<E> {

        private final Map<E, ImmutableSet<E>> transitionTable = new HashMap<>();
        private final Map<E, Recognizer> recognizers = new HashMap<>();
        private E startState;
        private E finishState;

        @Override
        public Builder<E> setStartState(E startState) {
            this.startState = startState;
            return this;
        }

        @Override
        public Builder<E> setFinishState(E finishState) {
            this.finishState = finishState;
            return this;
        }

        @Override
        public Builder<E> addTransition(E fsmState, ImmutableSet<E> fsmStateSet) {
            checkNotNull(fsmState);
            checkNotNull(fsmStateSet);
            transitionTable.put(fsmState, fsmStateSet);
            return this;
        }

        @Override
        public Builder<E> addRecogniser(E fsmState, Recognizer recognizer) {
            checkNotNull(fsmState);
            checkNotNull(recognizer);
            recognizers.put(fsmState, recognizer);
            return this;
        }

        @Override
        public FiniteStateMachine<E> build() {
            var immutableTransitionTable = ImmutableMap.copyOf(transitionTable);
            var immutableRecognizers = ImmutableMap.copyOf(recognizers);
            return new FiniteStateMachine<>(startState, finishState, immutableTransitionTable, immutableRecognizers);
        }
    }
}
