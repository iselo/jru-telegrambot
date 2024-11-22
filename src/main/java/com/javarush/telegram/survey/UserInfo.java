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
        this.name = checkNotNull(builder.name);
        this.gender = checkNotNull(builder.gender);
        this.age = checkNotNull(builder.age);
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
    public static final class Builder {

        private String name;
        private String gender;
        private String age;

        private Builder() {
        }

        @CanIgnoreReturnValue
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAge(String age) {
            this.age = age;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(this);
        }
    }
}
