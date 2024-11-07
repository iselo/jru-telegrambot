package com.javarush.telegram.responder;

import com.javarush.telegram.MultiSessionTelegramBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.menubutton.SetChatMenuButton;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeChat;
import org.telegram.telegrambots.meta.api.objects.menubutton.MenuButtonCommands;

import javax.annotation.concurrent.Immutable;
import java.util.List;

@Immutable
public final class ChatMenu extends RespondProcess<Boolean>{

    private final List<BotCommand> menu;

    public ChatMenu(List<BotCommand> menu) {
        this.menu = menu;
    }

    @Override
    protected Boolean execute(MultiSessionTelegramBot bot, Long chatId) {
        SetMyCommands commands = new SetMyCommands();

        commands.setCommands(menu);
        commands.setScope(BotCommandScopeChat.builder().chatId(chatId).build());
        bot.customSendApiMethod(commands);

        SetChatMenuButton menuButton = new SetChatMenuButton();
        menuButton.setChatId(chatId);
        menuButton.setMenuButton(MenuButtonCommands.builder().build());
        return bot.customSendApiMethod(menuButton);
    }
}
