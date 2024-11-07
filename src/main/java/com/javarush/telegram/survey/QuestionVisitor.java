package com.javarush.telegram.survey;

public interface QuestionVisitor {

    void visit(FirstQuestion question, String previousAnswer);

    void visit(SexQuestion question, String previousAnswer);

    void visit(AgeQuestion question, String previousAnswer);

    void visit(LastQuestion question, String previousAnswer);
}
