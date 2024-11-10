package com.javarush.telegram.fsm.instructions;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.responder.Menu;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

import static com.javarush.telegram.DialogMode.CHAT;
import static com.javarush.telegram.DialogMode.DATE;
import static com.javarush.telegram.DialogMode.GPT;
import static com.javarush.telegram.DialogMode.OPENER;
import static com.javarush.telegram.DialogMode.PROFILE;
import static com.javarush.telegram.DialogMode.START;

@Immutable
public final class MenuInstruction extends Instruction {

    private static final String MAIN = "main";

    private final List<BotCommand> menu = new ArrayList<>();

    public MenuInstruction() {
        configure();
    }

    @Override
    protected void execute(Responder responder, TelegramBotContext context) {
        var text = TelegramBotFileUtil.loadMessage(MAIN);
        responder.execute(new TextMessage(text));
        responder.execute(new Menu(menu));
    }

    private void configure() {
        menu.addAll(
                List.of(
                        new BotCommand(START.toString(), "Start"),
                        new BotCommand(GPT.toString(), "Ask chatGPT"),
                        new BotCommand(PROFILE.toString(), "Profile"),
                        new BotCommand(OPENER.toString(), "Dating message"),
                        new BotCommand(CHAT.toString(), "Chat from your identity"),
                        new BotCommand(DATE.toString(), "Chat with celebrity \uD83D\uDD25")
                )
        );
    }
}
