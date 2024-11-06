package com.javarush.telegram.survey;

public interface QuestionVisitor {

    void visit(FirstNameQuestion question, String noPreviousAnswer);

    void visit(GenderQuestion question, String previousAnswer);

    void visit(AgeQuestion question, String previousAnswer);

    void visit(LastEmptyQuestion question, String previousAnswer);
}
