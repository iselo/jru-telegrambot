package com.javarush.telegram;

public interface IUserInfoBuilder {

    IUserInfoBuilder setName(String name);

    IUserInfoBuilder setSex(String sex);

    IUserInfoBuilder setAge(String age);

    UserInfo build();
}
