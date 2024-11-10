package com.javarush.telegram.survey;

import com.google.errorprone.annotations.Immutable;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class UserInfo {

    private final String name;
    private final String gender;
    private final String age;

    private UserInfo(String name, String gender, String age) {
        this.name = checkNotNull(name);
        this.gender = checkNotNull(gender);
        this.age = checkNotNull(age);
    }

    /**
     * Returns a new builder of {@code UserInfo} instance.
     */
    public static IUserInfoBuilder newBuilder() {
        return new UserInfoBuilder();
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
        result += fieldToString(gender, "Стать");
        result += fieldToString(age, "Вік");

        return result;
    }

    private String fieldToString(String str, String description) {
        if (str != null && !str.isEmpty()) {
            return description + ": " + str + "\n";
        } else {
            return "";
        }
    }

    /**
     * A builder of {@code UserInfo} instance.
     */
    private static class UserInfoBuilder implements IUserInfoBuilder {

        private String name;
        private String gender;
        private String age;

        /**
         * @inheritDoc
         */
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
        public UserInfoBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        /**
         * @inheritDoc
         */
        public UserInfo build() {
            return new UserInfo(name, gender, age);
        }
    }
}
