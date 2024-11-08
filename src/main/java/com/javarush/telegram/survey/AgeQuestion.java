package com.javarush.telegram.survey;

import javax.annotation.concurrent.Immutable;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class AgeQuestion extends AbstractQuestion implements Question {

    private final static String AGE = "Your age:";

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
