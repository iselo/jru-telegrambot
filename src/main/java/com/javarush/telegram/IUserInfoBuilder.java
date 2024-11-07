package com.javarush.telegram;

public interface IUserInfoBuilder {

    /**
     * Sets name.
     *
     * @param name {@code String} value
     * @return reference of the builder
     */
    IUserInfoBuilder setName(String name);

    /**
     * Sets sex.
     *
     * @param sex {@code String} value
     * @return reference of the builder
     */
    IUserInfoBuilder setSex(String sex);

    /**
     * Sets age.
     *
     * @param age {@code String} value
     * @return reference of the builder
     */
    IUserInfoBuilder setAge(String age);

    /**
     * Returns new instance of {@code UserInfo}
     *
     * @return new {@code UserInfo} instance
     */
    UserInfo build();
}
