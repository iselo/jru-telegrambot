package com.javarush.telegram.survey;

import com.google.common.eventbus.Subscribe;
import com.javarush.telegram.eventbus.events.SurveyEvent;

/**
 * Represents survey that gathers information about a user to build the corresponding instance of
 * {@code UserInfo}.
 */
public final class UserInfoSurvey implements QuestionVisitor {

    private final UserInfo.Builder userInfoBuilder = UserInfo.newBuilder();
    private final SurveyQuestions questions = new SurveyQuestions();

    /**
     * Returns a new {@code UserInfo} instance.
     */
    public UserInfo newUserInfo() {
        return userInfoBuilder.build();
    }

    /**
     * Returns the questions of this survey.
     */
    public SurveyQuestions questions() {
        return questions;
    }

    /**
     * Skips to handle first question because no previous answer.
     */
    @Override
    public void visit(FirstNameQuestion question, String noPreviousAnswer) {
        // Intentionally empty
    }

    /**
     * Sets name value.
     */
    @Override
    public void visit(GenderQuestion question, String previousAnswer) {
        userInfoBuilder.setName(previousAnswer);
    }

    /**
     * Sets gender value.
     */
    @Override
    public void visit(AgeQuestion question, String previousAnswer) {
        userInfoBuilder.setGender(previousAnswer);
    }

    /**
     * Sets age value.
     */
    @Override
    public void visit(LastEmptyQuestion question, String previousAnswer) {
        userInfoBuilder.setAge(previousAnswer);
    }

    @Subscribe
    public void handle(SurveyEvent event) {
        var consumer = event.consumer();
        if (consumer != null) {
            consumer.accept(this);
        }
    }

}
