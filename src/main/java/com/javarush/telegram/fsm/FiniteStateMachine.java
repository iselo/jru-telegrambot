package com.javarush.telegram.fsm;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
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
    private FiniteStateMachine(Builder<E> builder) {
        this.startState = checkNotNull(builder.startState);
        this.finishState = checkNotNull(builder.finishState);
        this.transitionTable = ImmutableMap.copyOf(builder.transitionTable);
        this.recognizers = ImmutableMap.copyOf(builder.recognizers);
    }

    /**
     * Returns a new builder of {@code FiniteStateMachine} instance.
     *
     * @param <E> type of {@code FiniteStateMachine}
     * @return a new instance of the builder
     */
    public static <E extends Enum<?>> Builder<E> newBuilder() {
        return new Builder<>();
    }

    /**
     * Runs finite state machine to recognize states.
     *
     * @param update  the current Telegram update
     * @param context the Telegram bot context
     * @return result of the finite state machine run
     */
    public Result run(Update update,
                      TelegramBotContext context,
                      Chronology fsmOutput) {
        E currentFsmState = startState;

        while (currentFsmState != finishState) {
            Set<E> transitions = transitions(currentFsmState);

            Optional<E> nextFsmState = moveForward(transitions, update, context, fsmOutput);

            if (nextFsmState.isEmpty()) {

                return currentFsmState == startState ?
                        Result.NOT_STARTED :
                        Result.DEADLOCK;
            }

            currentFsmState = nextFsmState.get();
        }
        return Result.FINISHED;
    }

    private Set<E> transitions(E fsmState) {
        return transitionTable.get(fsmState);
    }

    private Recognizer recognizer(E fsmState) {
        return recognizers.get(fsmState);
    }

    private Optional<E> moveForward(Iterable<E> transitions,
                                    Update update,
                                    TelegramBotContext context,
                                    Chronology fsmOutput) {
        for (E fsmState : transitions) {

            var recognizer = recognizer(fsmState);

            if (recognizer.accept(update, context, fsmOutput)) {
                return Optional.of(fsmState);
            }
        }
        return Optional.empty();
    }

    /**
     * Represents a returned result by finite state machine after running.
     */
    public enum Result {
        DEADLOCK,
        FINISHED,
        NOT_STARTED
    }

    /**
     * A builder of finite state machine of a given type.
     *
     * @param <E> the builder type
     */
    @SuppressWarnings("Immutable")
    public static final class Builder<E extends Enum> {

        private final Map<E, ImmutableSet<E>> transitionTable = new HashMap<>();
        private final Map<E, Recognizer> recognizers = new HashMap<>();
        private E startState;
        private E finishState;

        private Builder() {
        }

        public Builder<E> setStartState(E startState) {
            this.startState = startState;
            return this;
        }

        public Builder<E> setFinishState(E finishState) {
            this.finishState = finishState;
            return this;
        }

        public Builder<E> addTransition(E fsmState, ImmutableSet<E> fsmStateSet) {
            checkNotNull(fsmState);
            checkNotNull(fsmStateSet);
            transitionTable.put(fsmState, fsmStateSet);
            return this;
        }

        public Builder<E> addRecogniser(E fsmState, Recognizer recognizer) {
            checkNotNull(fsmState);
            checkNotNull(recognizer);
            recognizers.put(fsmState, recognizer);
            return this;
        }

        public FiniteStateMachine<E> build() {
            return new FiniteStateMachine<>(this);
        }
    }
}
