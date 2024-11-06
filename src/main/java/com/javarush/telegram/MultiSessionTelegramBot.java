package com.javarush.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

public abstract class MultiSessionTelegramBot extends TelegramLongPollingBot {

    private final String name;
    private final String token;

    private final ThreadLocal<Update> updateEvent = new ThreadLocal<>();

    public MultiSessionTelegramBot(String name, String token) {
        this.name = name;
        this.token = token;
    }

//    public <T extends Serializable, Method extends BotApiMethod<T>> T sendApiMethodAsync(Method method) {
//        super.sendApiMethodAsync(method);
//        return null;
//    }

    public <T extends Serializable, Method extends BotApiMethod<T>> T customSendApiMethod(Method message) {
        try {
            return super.sendApiMethod(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public final void onUpdateReceived(Update updateEvent) {
        try {
            this.updateEvent.set(updateEvent);
            onUpdateEventReceived(this.updateEvent.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void onUpdateEventReceived(Update updateEvent) throws Exception;

    public String getCallbackQueryButtonKey() {
        return updateEvent.get().hasCallbackQuery() ? updateEvent.get().getCallbackQuery().getData() : "";
    }

    /*    *//**
     * Метод повертає ID поточного Telegram-чату
     *//*
    public Long getCurrentChatId() {
        if (updateEvent.get().hasMessage()) {
            return updateEvent.get().getMessage().getFrom().getId();
        }

        if (updateEvent.get().hasCallbackQuery()) {
            return updateEvent.get().getCallbackQuery().getFrom().getId();
        }

        return null;
    }

    *//**
     * Метод вертає текст з останнього повідомлення Telegram-чату
     *//*
    public String getMessageText() {
        return updateEvent.get().hasMessage() ? updateEvent.get().getMessage().getText() : "";
    }

    public boolean isMessageCommand() {
        return updateEvent.get().hasMessage() && updateEvent.get().getMessage().isCommand();
    }

    *//**
     * Метод повертає код натиснутої кнопки (buttonKey).
     * Мова йде про кнопки, які були додані до повідомлення.
     *//*
    public String getCallbackQueryButtonKey() {
        return updateEvent.get().hasCallbackQuery() ? updateEvent.get().getCallbackQuery().getData() : "";
    }

    *//**
     * Метод надсилає в чат ТЕКСТ (текстове повідомлення).
     * Підтримується markdown-розмітка.
     *//*
    public Message sendTextMessage(String text) {
        SendMessage command = createApiSendMessageCommand(text);
        return executeTelegramApiMethod(command);
    }

    *//**
     * Метод відправляє в чат ФОТО (Зображення).
     * Зображення задається ключем – photoKey.
     * Всі зображення містяться в папці resources/images
     *//*
    public Message sendPhotoMessage(String photoKey) {
        SendPhoto command = createApiPhotoMessageCommand(photoKey, null);
        return executeTelegramApiMethod(command);
    }

    *//**
     * Метод відправляє в чат ФОТО (Зображення) та ТЕКСТ.
     * Зображення задається ключем – photoKey.
     * Всі зображення містяться в папці resources/images
     *//*
    public Message sendPhotoTextMessage(String photoKey, String text) {
        SendPhoto command = createApiPhotoMessageCommand(photoKey, text);
        return executeTelegramApiMethod(command);
    }

    *//**
     * Метод змінює ТЕКСТ у раніше надісланному повідомленні.
     *//*
    public void updateTextMessage(Message message, String text) {
        EditMessageText command = new EditMessageText();
        command.setChatId(message.getChatId());
        command.setMessageId(message.getMessageId());
        command.setText(text);
        executeTelegramApiMethod(command);
    }

    *//**
     * Повідомлення з кнопками (Inline Buttons)
     *//*
    public Message sendTextButtonsMessage(String text, String... buttons) {
        SendMessage command = createApiSendMessageCommand(text);
        if (buttons.length > 0)
            attachButtons(command, List.of(buttons));

        return executeTelegramApiMethod(command);
    }

    *//**
     * Повідомлення з кнопками (Inline Buttons)
     *//*
    public void sendTextButtonsMessage(String text, List<String> buttons) {
        SendMessage command = createApiSendMessageCommand(text);
        if (buttons != null && !buttons.isEmpty())
            attachButtons(command, buttons);

        executeTelegramApiMethod(command);
    }


    public List<List<PhotoSize>> getUserProfilePhotos() {
        var userId = getCurrentChatId();
        var request = GetUserProfilePhotos.builder().userId(userId).offset(0).limit(100).build();
        UserProfilePhotos userProfilePhotos = executeTelegramApiMethod(request);
        return userProfilePhotos.getPhotos();
    }

    public List<List<PhotoSize>> getChatBotProfilePhotos() {
        var me = executeTelegramApiMethod(new GetMe());
        var userId = me.getId();
        var request = GetUserProfilePhotos.builder().userId(userId).offset(0).limit(100).build();
        UserProfilePhotos userProfilePhotos = executeTelegramApiMethod(request);
        return userProfilePhotos.getPhotos();
    }

    private SendMessage createApiSendMessageCommand(String text) {
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
        message.setChatId(getCurrentChatId());
        return message;
    }

    private void attachButtons(SendMessage message, List<String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (int i = 0; i < buttons.size(); i += 2) {
            String buttonName = buttons.get(i);
            String buttonValue = buttons.get(i + 1);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);

            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    private SendPhoto createApiPhotoMessageCommand(String photoKey, String text) {
        try {
            InputFile inputFile = new InputFile();
            var is = TelegramBotFileUtil.loadImage(photoKey);
            inputFile.setMedia(is, photoKey);

            SendPhoto photo = new SendPhoto();
            photo.setPhoto(inputFile);
            photo.setChatId(getCurrentChatId());

            if (text != null && !text.isEmpty())
                photo.setCaption(text);

            return photo;
        } catch (Exception e) {
            throw new RuntimeException("Can't create photo message!");
        }
    }


    private <T extends Serializable, Method extends BotApiMethod<T>> T executeTelegramApiMethod(Method method) {
        try {
            return super.sendApiMethod(method);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private Message executeTelegramApiMethod(SendPhoto message) {
        try {
            return super.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }*/
}
