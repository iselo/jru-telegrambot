package com.javarush.telegram.survey;

import java.util.Optional;

public abstract class AbstractQuestion {

    private final Optional<String> value;

    protected AbstractQuestion(Optional<String> value) {
        this.value = value;
    }

    /**
     * Returns question value.
     */
    public Optional<String> value() {
        return value;
    }

}
