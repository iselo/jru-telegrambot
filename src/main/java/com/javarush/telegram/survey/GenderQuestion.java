package com.javarush.telegram.survey;

import com.google.errorprone.annotations.Immutable;

import java.util.Optional;

@Immutable
public final class GenderQuestion extends Question {

    private static final String GENDER = "Gender:";

    public GenderQuestion() {
        super(Optional.of(GENDER));
    }

    @Override
    protected void handle(QuestionVisitor visitor, String previousAnswer) {
        visitor.visit(this, previousAnswer);
    }
}
