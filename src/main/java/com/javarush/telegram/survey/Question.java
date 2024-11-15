package com.javarush.telegram.survey;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents a survey question.
 */
public abstract class Question {

    private final Optional<String> value;

    protected Question(Optional<String> value) {
        this.value = checkNotNull(value);
    }

    /**
     * Returns a question value.
     */
    public Optional<String> value() {
        return value;
    }

    /**
     * Accepts visitor to handle answer of the previous question.
     */
    public final void accept(QuestionVisitor visitor, String previousAnswer) {
        checkNotNull(visitor);
        checkNotNull(previousAnswer);
        handle(visitor, previousAnswer);
    }

    protected abstract void handle(QuestionVisitor visitor, String previousAnswer);
}
