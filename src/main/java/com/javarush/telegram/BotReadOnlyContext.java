package com.javarush.telegram;

import com.javarush.telegram.survey.UserInfoSurvey;

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
