package com.javarush.telegram.survey;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Optional;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class AgeQuestion extends AbstractQuestion implements Question {

    private static final String AGE = "Your age:";

    public AgeQuestion() {
        super(Optional.of(AGE));
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
