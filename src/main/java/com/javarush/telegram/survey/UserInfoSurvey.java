package com.javarush.telegram.survey;


import javax.annotation.concurrent.Immutable;

/**
 * Represents survey that gathers information about a user to build the corresponding instance of
 * {@code UserInfo}.
 */
@Immutable
public final class UserInfoSurvey implements QuestionVisitor {

    private final IUserInfoBuilder userInfoBuilder = UserInfo.newBuilder();

    public UserInfo newUserInfo() {
        return userInfoBuilder.build();
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
     *
     * @param question       instance of the {@code Question}
     * @param previousAnswer
     */
    @Override
    public void visit(LastEmptyQuestion question, String previousAnswer) {
        userInfoBuilder.setAge(previousAnswer);
    }
}
