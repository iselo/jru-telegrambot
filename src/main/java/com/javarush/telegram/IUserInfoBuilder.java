package com.javarush.telegram;

import com.javarush.telegram.questions.QuestionVisitor;

public interface IUserInfoBuilder extends QuestionVisitor {

    UserInfo build();
}
