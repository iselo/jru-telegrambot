package com.javarush.telegram.eventbus.handlers;

import com.google.common.collect.ImmutableMap;
import com.google.common.eventbus.Subscribe;
import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotFileUtil;
import com.javarush.telegram.eventbus.events.DateDialogEvent;
import com.javarush.telegram.responder.PhotoMessage;
import com.javarush.telegram.responder.TextButtonsMessage;

@Immutable
public final class OnDateDialog implements EventHandler<DateDialogEvent> {

    private static final String DATE = "date";

    private final ImmutableMap<String, String> buttons = ImmutableMap.of(
            "Аріана Гранде \uD83D\uDD25", "date_grande",
            "Марго Роббі \uD83D\uDD25\uD83D\uDD25", "date_robbie",
            "Зендея \uD83D\uDD25\uD83D\uDD25\uD83D\uDD25", "date_zendaya",
            "Райан Гослінг \uD83D\uDE0E", "date_gosling",
            "Том Харді \uD83D\uDE0E\uD83D\uDE0E", "date_hardy"
    );

    @Override
    @Subscribe
    public void handle(DateDialogEvent event) {
        var text = TelegramBotFileUtil.loadMessage(DATE);
        var responder = event.responder();
        responder.execute(new PhotoMessage(DATE));
        responder.execute(new TextButtonsMessage(text, buttons));
    }
}