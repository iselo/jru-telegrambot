package com.javarush.telegram.survey;

import com.google.errorprone.annotations.Immutable;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Immutable
public class UserInfo {

    private final String name;
    private final String gender;
    private final String age;
}
