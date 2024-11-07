package com.javarush.telegram.questions;

import java.util.Optional;

public abstract class QuestionCore {

    private final Optional<String> question;

    protected QuestionCore(Optional<String> question) {
        this.question = question;
    }

    public Optional<String> value() {
        return question;
    }

}
