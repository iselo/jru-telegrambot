package com.javarush.telegram;

import com.javarush.telegram.responder.ChatMenu;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.List;

import static com.javarush.telegram.DialogMode.*;

@Immutable
public final class StartDialog extends AbstractMessage {

    private final List<BotCommand> menu = new ArrayList<>();

    public StartDialog(TelegramBotContext context) {
        super(context);
        configure();
    }

    private void configure() {
        menu.addAll(
                List.of(
                        new BotCommand(START.toString(), "Start bot"),
                        new BotCommand(PROFILE.toString(), "Create profile"),
                        new BotCommand(OPENER.toString(), "Dating message"),
                        new BotCommand(MESSAGE.toString(), "Chat from your identity"),
                        new BotCommand(DATE.toString(), "Chat with celebrity \uD83D\uDD25"),
                        new BotCommand(GPT.toString(), "Ask chatGPT")
                )
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();

        if (messageText.equalsIgnoreCase(START.toString())) {
            context().setMode(START);

            Responder responder = new Responder(bot, getChatId(update));

            String text = TelegramBotFileUtil.loadMessage("main");
            responder.execute(new TextMessage(text));

            responder.execute(new ChatMenu(menu));

            return true;
        }
        return false;
    }
}
