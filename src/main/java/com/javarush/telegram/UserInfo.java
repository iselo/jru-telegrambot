package com.javarush.telegram;

import com.javarush.telegram.questions.AgeQuestion;
import com.javarush.telegram.questions.FirstQuestion;
import com.javarush.telegram.questions.LastQuestion;
import com.javarush.telegram.questions.SexQuestion;

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

        @Override
        public void visit(FirstQuestion question, String previousAnswer) {
            // Intentionally empty
        }

        @Override
        public void visit(SexQuestion question, String previousAnswer) {
            this.name = previousAnswer;
        }

        @Override
        public void visit(AgeQuestion question, String previousAnswer) {
            this.sex = previousAnswer;
        }

        @Override
        public void visit(LastQuestion question, String previousAnswer) {
            this.age = previousAnswer;
        }

        /**
         * @inheritDoc
         */
        public UserInfo build() {
            return new UserInfo(name, sex, age);
        }
    }
}
