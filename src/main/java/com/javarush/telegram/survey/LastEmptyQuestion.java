package com.javarush.telegram.survey;

import com.google.errorprone.annotations.Immutable;

import java.util.Optional;

@Immutable
public final class LastEmptyQuestion extends Question {

    public LastEmptyQuestion() {
        super(Optional.empty());
    }

    @Override
    protected void handle(QuestionVisitor visitor, String previousAnswer) {
        visitor.visit(this, previousAnswer);
    }
}
