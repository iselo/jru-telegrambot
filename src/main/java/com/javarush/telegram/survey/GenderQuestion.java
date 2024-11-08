package com.javarush.telegram.survey;

import javax.annotation.concurrent.Immutable;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class GenderQuestion extends AbstractQuestion implements Question {

    private final static String GENDER = "Your gender:";

    public GenderQuestion() {
        super(Optional.of(GENDER));
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
