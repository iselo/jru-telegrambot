package com.javarush.telegram.survey;

/**
 * Represents survey that gathers information about a user to build the corresponding instance of
 * {@code UserInfo}.
 */
public final class UserInfoSurvey implements QuestionVisitor {

    private final IUserInfoBuilder userInfoBuilder = UserInfo.newBuilder();
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
        @SuppressWarnings("unused")
        var unused = userInfoBuilder.setName(previousAnswer);
    }

    /**
     * Sets gender value.
     */
    @Override
    public void visit(AgeQuestion question, String previousAnswer) {
        @SuppressWarnings("unused")
        var unused = userInfoBuilder.setGender(previousAnswer);
    }

    /**
     * Sets age value.
     */
    @Override
    public void visit(LastEmptyQuestion question, String previousAnswer) {
        @SuppressWarnings("unused")
        var unused = userInfoBuilder.setAge(previousAnswer);
    }
}
