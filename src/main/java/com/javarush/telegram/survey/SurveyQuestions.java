package com.javarush.telegram.survey;

import com.google.common.eventbus.Subscribe;
import com.javarush.telegram.eventbus.events.OpenerDialogEvent;
import com.javarush.telegram.eventbus.events.ProfileDialogEvent;

import java.util.ArrayList;
import java.util.List;

public class SurveyQuestions {

    private static final int TOP_ELEMENT = 0;

    private final List<Question> questions = new ArrayList<>();

    public SurveyQuestions() {
        resetQuestions();
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
    @Subscribe
    public void handle(ProfileDialogEvent event) {
        resetQuestions();
    }

    @Subscribe
    public void handle(OpenerDialogEvent event) {
        resetQuestions();
    }

    /**
     * Returns {@code true} if questions of this survey are present, otherwise {@code false}.
     */
    public boolean isPresent() {
        return !questions.isEmpty();
    }

    private void resetQuestions() {
        questions.clear();
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
