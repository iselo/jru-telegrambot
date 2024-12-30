package com.javarush.telegram.survey;

import com.google.common.eventbus.Subscribe;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.SurveyEvent;
import com.javarush.telegram.survey.UserInfo.UserInfoBuilder;

/**
 * Represents survey that gathers information about a user to build the corresponding instance of
 * {@code UserInfo}.
 */
public final class UserInfoSurvey implements QuestionVisitor, Subscribable {

    private final UserInfoBuilder userInfoBuilder = UserInfo.builder();
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
    @SuppressWarnings("unused")
    public void visit(GenderQuestion question, String previousAnswer) {
        var ignoreReturnValue = userInfoBuilder.name(previousAnswer);
    }

    /**
     * Sets gender value.
     */
    @Override
    @SuppressWarnings("unused")
    public void visit(AgeQuestion question, String previousAnswer) {
        var ignoreReturnValue = userInfoBuilder.gender(previousAnswer);
    }

    /**
     * Sets age value.
     */
    @Override
    @SuppressWarnings("unused")
    public void visit(LastEmptyQuestion question, String previousAnswer) {
        var ignoreReturnValue = userInfoBuilder.age(previousAnswer);
    }

    @Subscribe
    public void handle(SurveyEvent event) {
        event.getConsumer().accept(this);
    }
}
