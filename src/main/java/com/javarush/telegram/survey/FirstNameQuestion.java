package com.javarush.telegram.survey;

import com.google.errorprone.annotations.Immutable;

import java.util.Optional;

@Immutable
public final class FirstNameQuestion extends Question {

    private static final String NAME = "Name:";

    public FirstNameQuestion() {
        super(Optional.of(NAME));
    }

    @Override
    protected void handle(QuestionVisitor visitor, String previousAnswer) {
        visitor.visit(this, previousAnswer);
    }
}
