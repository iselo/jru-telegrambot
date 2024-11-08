package com.javarush.telegram.survey;

import java.util.Optional;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class FirstNameQuestion extends AbstractQuestion implements Question {

    private final static String NAME = "Your name:";

    public FirstNameQuestion() {
        super(Optional.of(NAME));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void accept(QuestionVisitor visitor, String previousAnswer) {
        // Intentionally empty
    }

}
