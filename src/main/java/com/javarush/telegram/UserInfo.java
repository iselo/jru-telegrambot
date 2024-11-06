package com.javarush.telegram;

public final class UserInfo {
    private final String name; //Ім'я
    private final String sex; //Стать
    private final String age; //Вік

    private UserInfo(String name, String sex, String age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    private String fieldToString(String str, String description) {
        if (str != null && !str.isEmpty())
            return description + ": " + str + "\n";
        else
            return "";
    }

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

    private static class UserInfoBuilder implements IUserInfoBuilder {

        private String name;
        private String sex;
        private String age;

        public UserInfoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserInfoBuilder setAge(String age) {
            this.age = age;
            return this;
        }
        public UserInfoBuilder setSex(String sex) {
            this.sex = sex;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(name, sex, age);
        }
    }
}
