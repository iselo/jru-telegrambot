package com.javarush.telegram.survey;

import javax.annotation.concurrent.Immutable;

import static org.glassfish.jersey.internal.guava.Preconditions.checkNotNull;

/**
 * A user info
 */
@Immutable
public final class UserInfo {

    private final String name;

    private final String sex;

    private final String age;

    private UserInfo(String name, String sex, String age) {
        this.name = checkNotNull(name);
        this.sex = checkNotNull(sex);
        this.age = checkNotNull(age);
    }

    /**
     * Returns string representation of the instance.
     *
     * @return {@code String} representation of {@code UserInfo} instance
     */
    @Override
    public String toString() {
        String result = "";

        result += fieldToString(name, "Ім'я");
        result += fieldToString(sex, "Стать");
        result += fieldToString(age, "Вік");

        return result;
    }

    public static IUserInfoBuilder newBuilder() {
        return new UserInfoBuilder();
    }

    private String fieldToString(String str, String description) {
        if (str != null && !str.isEmpty())
            return description + ": " + str + "\n";
        else
            return "";
    }

    /**
     * A builder of {@code UserInfo} instance.
     */
    private static class UserInfoBuilder implements IUserInfoBuilder {

        private String name;

        private String sex;

        private String age;

        public UserInfoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * @inheritDoc
         */
        public UserInfoBuilder setAge(String age) {
            this.age = age;
            return this;
        }

        /**
         * @inheritDoc
         */
        public UserInfoBuilder setSex(String sex) {
            this.sex = sex;
            return this;
        }

        /**
         * @inheritDoc
         */
        public UserInfo build() {
            return new UserInfo(name, sex, age);
        }
    }
}
