package com.javarush.telegram.questions;

import javax.annotation.concurrent.Immutable;
import java.util.Optional;

@Immutable
public final class LastQuestion extends QuestionCore implements Question {

    public LastQuestion(Optional<String> question) {
        super(question);
    }

    @Override
    public void accept(QuestionVisitor visitor, String previousAnswer) {
        visitor.visit(this, previousAnswer);
    }
}
