package com.javarush.telegram.survey;

import java.util.ArrayList;
import java.util.List;

public class SurveyQuestions {

    private static final int TOP_ELEMENT = 0;

    private final List<Question> questions = new ArrayList<>();

    public SurveyQuestions() {
        setQuestions();
    }

    /**
     * Returns a next question of this survey.
     */
    public Question nextQuestion() {
        return questions.remove(TOP_ELEMENT);
    }

    /**
     * Resets questions of this survey to initial state.
     */
    public void resetQuestions() {
        questions.clear();
        setQuestions();
    }

    /**
     * Returns {@code true} if questions of this survey are present, otherwise {@code false}.
     */
    public boolean isPresent() {
        return !questions.isEmpty();
    }

    private void setQuestions() {
        questions.addAll(
                List.of(
                        new FirstNameQuestion(),
                        new GenderQuestion(),
                        new AgeQuestion(),
                        new LastEmptyQuestion()
                )
        );
    }
}
