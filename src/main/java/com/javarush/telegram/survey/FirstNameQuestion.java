package com.javarush.telegram.survey;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Optional;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class FirstNameQuestion extends AbstractQuestion implements Question {

    private static final String NAME = "Your name:";

    public FirstNameQuestion() {
        super(Optional.of(NAME));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void accept(QuestionVisitor visitor, String previousAnswer) {
        checkNotNull(visitor);
        visitor.visit(this, previousAnswer);
    }

}
