package com.javarush.telegram.survey;

import java.util.Optional;

public interface Question {

    /**
     * Returns current question value.
     */
    Optional<String> value();

    /**
     * Accepts visitor to handle answer for the previous question.
     */
    void accept(QuestionVisitor visitor, String previousAnswer);
}
