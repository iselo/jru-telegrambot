package com.javarush.telegram.survey;

import javax.annotation.concurrent.Immutable;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

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
