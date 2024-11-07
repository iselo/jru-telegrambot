package com.javarush.telegram.questions;

import java.util.Optional;

public interface Question {

    Optional<String> value();

    void accept(QuestionVisitor visitor, String previousAnswer);
}
