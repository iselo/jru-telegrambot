package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

interface ResponseMessage {

    boolean apply(MultiSessionTelegramBot bot, Update update);
}
