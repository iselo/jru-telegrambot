package com.javarush.telegram.survey;

import javax.annotation.concurrent.Immutable;
import java.util.Optional;

@Immutable
public final class AgeQuestion extends QuestionCore implements Question{

    public AgeQuestion(Optional<String> question) {
        super(question);
    }

    @Override
    public void accept(QuestionVisitor visitor, String previousAnswer) {
        visitor.visit(this, previousAnswer);
    }
}
