package com.javarush.telegram;

import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.menubutton.SetChatMenuButton;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeChat;
import org.telegram.telegrambots.meta.api.objects.menubutton.MenuButtonCommands;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.List;

@Immutable
public final class StartDialogEvent extends AbstractMessage {

    private final static String EVENT = "/start";

    private final List<BotCommand> menu = new ArrayList<>();

    public StartDialogEvent(TelegramBotContext context) {
        super(context);
        configure();
    }

    private void configure() {
        menu.addAll(
                List.of(
                        new BotCommand("/start", "Start bot"),
                        new BotCommand("/profile", "Create profile"),
                        new BotCommand("/opener", "Dating message"),
                        new BotCommand("/message", "Chat from your identity"),
                        new BotCommand("/date", "Chat with celebrity \uD83D\uDD25"),
                        new BotCommand("/gpt", "Ask chatGPT")
                )
        );
    }

    @Override
    protected boolean handle(MultiSessionTelegramBot bot, Update update) {
        String messageText = update.getMessage().getText();


        if (messageText.equalsIgnoreCase(EVENT)) {
            context().setMode(DialogMode.START);

            String text = TelegramBotFileUtil.loadMessage("main");
            sendTextMessage(bot, update, text);

            SetMyCommands commands = new SetMyCommands();
            Long chatId = getChatId(update);

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
