package com.javarush.telegram.survey;

/**
 * The builder interface for creating {@code UserInfo} instance.
 */
public interface IUserInfoBuilder {

    /**
     * Sets name.
     *
     * @param name {@code String} value
     * @return reference of the builder
     */
    IUserInfoBuilder setName(String name);

    /**
     * Sets gender.
     *
     * @param gender {@code String} value
     * @return reference of the builder
     */
    IUserInfoBuilder setGender(String gender);

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
