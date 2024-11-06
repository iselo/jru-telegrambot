package com.javarush.telegram.command;

import com.javarush.telegram.MultiSessionTelegramBot;
import com.javarush.telegram.TelegramBotException;
import com.javarush.telegram.TelegramBotFileUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.concurrent.Immutable;
import java.io.InputStream;

import static org.glassfish.jersey.internal.guava.Preconditions.checkNotNull;

@Immutable
public final class SendPhotoMessage extends Command<Message> {

    private final String pictureName;

    public SendPhotoMessage(String pictureName) {
        this.pictureName = checkNotNull(pictureName);
    }

    @Override
    protected Message execute(MultiSessionTelegramBot bot, Long chatId) {
        try {
            SendPhoto photo = createPhotoMessage(chatId, pictureName);
            return bot.execute(photo);
        } catch (TelegramApiException e) {
            throw new TelegramBotException(e.getMessage());
        }
    }

    private static SendPhoto createPhotoMessage(Long chatId, String pictureName) {
        InputFile inputFile = new InputFile();
        InputStream mediaStream = TelegramBotFileUtil.loadImage(pictureName);
        inputFile.setMedia(mediaStream, pictureName);

        SendPhoto photo = new SendPhoto();
        photo.setPhoto(inputFile);
        photo.setChatId(chatId);
        return photo;
    }
}
