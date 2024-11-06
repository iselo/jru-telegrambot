package com.javarush.telegram;

public final class UserInfo {
    private final String name; //Ім'я
    private final String sex; //Стать
    private final String age; //Вік
//    private final String city; //Місто
//    private final String occupation; //Професія
//    private final String hobby; //Хобі
//    public String handsome; //Краса, привабливість
//    public String wealth; //Дохід, багатство
//    public String annoys; //Мене дратує у людях
//    public String goals; //Цілі знайомства

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
//        result += fieldToString(city, "Місто");
//        result += fieldToString(occupation, "Професія");
//        result += fieldToString(hobby, "Хобі");
//        result += fieldToString(handsome, "Краса, привабливість у балах (максимум 10 балів)");
//        result += fieldToString(wealth, "Доход, богатство");
//        result += fieldToString(annoys, "В людях дратує");
//        result += fieldToString(goals, "Цілі знайомства");

        return result;
    }

    public static IUserInfoBuilder newBuilder() {
        return new UserInfoBuilder();
    }

    private static class UserInfoBuilder implements IUserInfoBuilder {

        public String name;
        public String sex;
        public String age;

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
