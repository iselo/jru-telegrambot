package com.javarush.telegram.fsm.instructions;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.Responder;
import com.javarush.telegram.responder.TextButtonsMessage;

import java.util.Map;

@Immutable
public final class DateDialogInstruction extends Instruction {

    private static final String DATE = "date";

    private final Map<String, String> buttons = Map.of(
            "Аріана Гранде \uD83D\uDD25", "date_grande",
            "Марго Роббі \uD83D\uDD25\uD83D\uDD25", "date_robbie",
            "Зендея \uD83D\uDD25\uD83D\uDD25\uD83D\uDD25", "date_zendaya",
            "Райан Гослінг \uD83D\uDE0E", "date_gosling",
            "Том Харді \uD83D\uDE0E\uD83D\uDE0E", "date_hardy"
    );

    @Override
    protected void execute(Responder responder, TelegramBotContext context) {
        var text = TelegramBotFileUtil.loadMessage(DATE);
        responder.execute(new PhotoMessage(DATE));
        responder.execute(new TextButtonsMessage(text, buttons));
    }
}
