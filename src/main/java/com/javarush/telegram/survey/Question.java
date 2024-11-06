package com.javarush.telegram.survey;

import java.util.Optional;

public interface Question {

    Optional<String> value();

    void accept(QuestionVisitor visitor, String previousAnswer);
}
