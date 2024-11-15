package com.javarush.telegram.survey;

import com.google.errorprone.annotations.Immutable;

import java.util.Optional;

@Immutable
public final class AgeQuestion extends Question {

    private static final String AGE = "Age:";

    public AgeQuestion() {
        super(Optional.of(AGE));
    }

    @Override
    protected void handle(QuestionVisitor visitor, String previousAnswer) {
        visitor.visit(this, previousAnswer);
    }
}
