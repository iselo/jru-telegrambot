package com.javarush.telegram.survey;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Optional;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class LastEmptyQuestion extends AbstractQuestion implements Question {

    public LastEmptyQuestion() {
        super(Optional.empty());
    }

    @Override
    public void accept(QuestionVisitor visitor, String previousAnswer) {
        checkNotNull(visitor);
        visitor.visit(this, previousAnswer);
    }
}
