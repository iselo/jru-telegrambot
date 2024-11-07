package com.javarush.telegram.survey;


import javax.annotation.concurrent.Immutable;

@Immutable
public final class Survey implements QuestionVisitor {

    private final IUserInfoBuilder userInfoBuilder = UserInfo.newBuilder();

    public UserInfo newUserInfo() {
        return userInfoBuilder.build();
    }

    @Override
    public void visit(FirstQuestion question, String previousAnswer) {
        // Intentionally empty
    }

    @Override
    public void visit(SexQuestion question, String previousAnswer) {
        userInfoBuilder.setName(previousAnswer);
    }

    @Override
    public void visit(AgeQuestion question, String previousAnswer) {
        userInfoBuilder.setSex(previousAnswer);

    }

    @Override
    public void visit(LastQuestion question, String previousAnswer) {
        userInfoBuilder.setAge(previousAnswer);

    }
}
