package com.javarush.telegram.questions;

import javax.annotation.concurrent.Immutable;
import java.util.Optional;

@Immutable
public final class FirstQuestion extends QuestionCore implements Question {

    public FirstQuestion(Optional<String> question) {
        super(question);
    }

    @Override
    public void accept(QuestionVisitor visitor, String previousAnswer) {
        // Intentionally empty
    }

}
