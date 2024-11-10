package com.javarush.telegram;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.survey.UserInfoSurvey;

@Immutable
public interface BotReadOnlyContext {

    /**
     * Returns current Telegram bot dialog mode.
     */
    DialogMode getMode();

    /**
     * Returns {@code UserInfoSurvey}.
     */
    UserInfoSurvey survey();
}
