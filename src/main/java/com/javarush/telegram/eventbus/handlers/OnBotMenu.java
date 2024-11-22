package com.javarush.telegram.eventbus.handlers;

import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.Subscribable;
import com.javarush.telegram.eventbus.events.MenuEvent;
import com.javarush.telegram.eventbus.events.StartDialogEvent;
import com.javarush.telegram.eventbus.events.TextMessageEvent;
import com.javarush.telegram.responder.Menu;
import com.javarush.telegram.responder.TextMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import static com.javarush.telegram.DialogModeState.CHAT;
import static com.javarush.telegram.DialogModeState.DATE;
import static com.javarush.telegram.DialogModeState.GPT;
import static com.javarush.telegram.DialogModeState.OPENER;
import static com.javarush.telegram.DialogModeState.PROFILE;
import static com.javarush.telegram.DialogModeState.START;

@Immutable
public final class OnBotMenu implements EventHandler<StartDialogEvent>, Subscribable {

    private static final String MAIN = "main";

    @SuppressWarnings("Immutable")
    private final ImmutableList<BotCommand> menu = ImmutableList.of(
            new BotCommand(START.toString(), "Start"),
            new BotCommand(GPT.toString(), "Ask chatGPT"),
            new BotCommand(PROFILE.toString(), "Profile"),
            new BotCommand(OPENER.toString(), "Dating message"),
            new BotCommand(CHAT.toString(), "Chat from your identity"),
            new BotCommand(DATE.toString(), "Chat with celebrity \uD83D\uDD25")
    );

    @Override
    @Subscribe
    public void handle(StartDialogEvent event) {
        var text = TelegramBotFileUtil.loadMessage(MAIN);
        new TextMessageEvent(new TextMessage(text)).post();
        new MenuEvent(new Menu(menu)).post();
    }
}
