package com.javarush.telegram.responder;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.MultiSessionTelegramBot;
import com.javarush.telegram.TelegramBotException;
import com.javarush.telegram.TelegramBotFileUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class PhotoMessage extends RespondProcess<Message> {

    private final String pictureName;

    public PhotoMessage(String pictureName) {
        this.pictureName = checkNotNull(pictureName);
    }

    private static SendPhoto createPhotoMessage(Long chatId, String pictureName) {
        var inputFile = new InputFile();
        var mediaStream = TelegramBotFileUtil.loadImage(pictureName);
        inputFile.setMedia(mediaStream, pictureName);

        var photo = new SendPhoto();
        photo.setPhoto(inputFile);
        photo.setChatId(chatId);
        return photo;
    }

    @Override
    protected Message execute(MultiSessionTelegramBot bot, Long chatId) {
        try {
            var photo = createPhotoMessage(chatId, pictureName);
            return bot.execute(photo);
        } catch (TelegramApiException e) {
            throw new TelegramBotException(e.getMessage());
        }
    }
}
