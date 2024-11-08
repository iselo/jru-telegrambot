package com.javarush.telegram.survey;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Optional;
import javax.annotation.concurrent.Immutable;

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
