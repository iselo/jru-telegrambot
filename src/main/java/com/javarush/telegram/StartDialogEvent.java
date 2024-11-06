package com.javarush.telegram;

import com.javarush.telegram.command.SendTextMessage;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.menubutton.SetChatMenuButton;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeChat;
import org.telegram.telegrambots.meta.api.objects.menubutton.MenuButtonCommands;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.List;

import static com.javarush.telegram.DialogMode.*;

@Immutable
public final class StartDialogEvent extends AbstractMessage {

    private final List<BotCommand> menu = new ArrayList<>();

    public StartDialogEvent(TelegramBotContext context) {
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

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();


        if (messageText.equalsIgnoreCase(START.toString())) {
            context().setMode(START);

            String text = TelegramBotFileUtil.loadMessage("main");
            Long chatId = getChatId(update);
            new SendTextMessage(text).handle(bot, chatId);

            SetMyCommands commands = new SetMyCommands();

            commands.setCommands(menu);
            commands.setScope(BotCommandScopeChat.builder().chatId(chatId).build());
            bot.customSendApiMethod(commands);

            SetChatMenuButton menuButton = new SetChatMenuButton();
            menuButton.setChatId(chatId);
            menuButton.setMenuButton(MenuButtonCommands.builder().build());
            bot.customSendApiMethod(menuButton);

            return true;
        }
        return false;
    }
}
