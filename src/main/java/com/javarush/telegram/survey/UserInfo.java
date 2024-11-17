package com.javarush.telegram.survey;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class UserInfo {

    private final String name;
    private final String gender;
    private final String age;

    private UserInfo(Builder builder) {
        this.name = builder.name;
        this.gender = builder.gender;
        this.age = builder.age;
    }

    /**
     * Returns a new builder of {@code UserInfo} instance.
     */
    public static Builder newBuilder() {
        return new Builder();
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
    public static class Builder {

        private String name;
        private String gender;
        private String age;

        private Builder() {
        }

        @CanIgnoreReturnValue
        public Builder setName(String name) {
            this.name = checkNotNull(name);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAge(String age) {
            this.age = checkNotNull(age);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setGender(String gender) {
            this.gender = checkNotNull(gender);
            return this;
        }

        public UserInfo build() {
            return new UserInfo(this);
        }
    }
}
