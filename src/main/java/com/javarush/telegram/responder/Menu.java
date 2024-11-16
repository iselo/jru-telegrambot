package com.javarush.telegram.responder;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.MultiSessionTelegramBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.menubutton.SetChatMenuButton;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeChat;
import org.telegram.telegrambots.meta.api.objects.menubutton.MenuButtonCommands;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class Menu extends RespondProcess<Boolean> {

    @SuppressWarnings("Immutable")
    private final ImmutableList<BotCommand> botCommands;

    public Menu(ImmutableList<BotCommand> botCommands) {
        this.botCommands = checkNotNull(botCommands);
    }

    @Override
    protected Boolean execute(MultiSessionTelegramBot bot, Long chatId) {
        var commands = new SetMyCommands();
        commands.setCommands(botCommands);
        commands.setScope(BotCommandScopeChat.builder().chatId(chatId).build());

        var menuButton = new SetChatMenuButton();
        menuButton.setChatId(chatId);
        menuButton.setMenuButton(MenuButtonCommands.builder().build());

        return bot.customSendApiMethod(commands) && bot.customSendApiMethod(menuButton);
    }
}
