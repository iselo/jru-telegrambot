package com.javarush.telegram.fsm;

import com.javarush.telegram.fsm.recognizers.BotMenuRecogniser;
import com.javarush.telegram.fsm.recognizers.BotModeRecogniser;
import com.javarush.telegram.fsm.recognizers.BotStartDialogRecognizer;
import com.javarush.telegram.fsm.recognizers.ChatDialogRecognizer;
import com.javarush.telegram.fsm.recognizers.ChatMessageAddRecognizer;
import com.javarush.telegram.fsm.recognizers.ChatMessageSendRecognizer;
import com.javarush.telegram.fsm.recognizers.DateCelebrityMessageRecognizer;
import com.javarush.telegram.fsm.recognizers.DateCelebritySelectRecognizer;
import com.javarush.telegram.fsm.recognizers.DateDialogRecognizer;
import com.javarush.telegram.fsm.recognizers.FinishRecognizer;
import com.javarush.telegram.fsm.recognizers.GptDialogRecognizer;
import com.javarush.telegram.fsm.recognizers.GptMessageRecognizer;
import com.javarush.telegram.fsm.recognizers.OpenerDialogRecognizer;
import com.javarush.telegram.fsm.recognizers.OpenerQuestionRecognizer;
import com.javarush.telegram.fsm.recognizers.ProfileDialogRecognizer;
import com.javarush.telegram.fsm.recognizers.ProfileQuestionRecognizer;

import java.util.EnumSet;

public enum FiniteStateMachineFactory { // NOSONAR

    MAIN() {
        @Override
        public FiniteStateMachine<MainFsmState> newInstance() {
            return FiniteStateMachine.<MainFsmState>newBuilder()
                    .setStartState(MainFsmState.START)
                    .setFinishState(MainFsmState.FINISH)
                    .addTransition(MainFsmState.START,
                            EnumSet.of(
                                    MainFsmState.BOT_MENU,
                                    MainFsmState.BOT_MODE
                            )
                    )
                    .addTransition(MainFsmState.BOT_MENU, EnumSet.of(MainFsmState.FINISH))
                    .addTransition(MainFsmState.BOT_MODE, EnumSet.of(MainFsmState.FINISH))
                    .addTransition(MainFsmState.FINISH, EnumSet.noneOf(MainFsmState.class))
                    .addRecogniser(MainFsmState.BOT_MENU, new BotMenuRecogniser())
                    .addRecogniser(MainFsmState.BOT_MODE, new BotModeRecogniser())
                    .addRecogniser(MainFsmState.FINISH, new FinishRecognizer())
                    .build();
        }
    },

    BOT_MENU() {
        @Override
        public FiniteStateMachine<MenuFsmState> newInstance() {
            return FiniteStateMachine.<MenuFsmState>newBuilder()
                    .setStartState(MenuFsmState.START)
                    .setFinishState(MenuFsmState.FINISH)
                    .addTransition(MenuFsmState.START,
                            EnumSet.of(
                                    MenuFsmState.MENU,
                                    MenuFsmState.GPT_DIALOG,
                                    MenuFsmState.PROFILE_DIALOG,
                                    MenuFsmState.OPENER_DIALOG,
                                    MenuFsmState.CHAT_DIALOG,
                                    MenuFsmState.DATE_DIALOG,
                                    MenuFsmState.FINISH
                            )
                    )
                    .addTransition(MenuFsmState.MENU, EnumSet.of(MenuFsmState.FINISH))
                    .addTransition(MenuFsmState.GPT_DIALOG, EnumSet.of(MenuFsmState.FINISH))
                    .addTransition(MenuFsmState.PROFILE_DIALOG, EnumSet.of(MenuFsmState.FINISH))
                    .addTransition(MenuFsmState.OPENER_DIALOG, EnumSet.of(MenuFsmState.FINISH))
                    .addTransition(MenuFsmState.CHAT_DIALOG, EnumSet.of(MenuFsmState.FINISH))
                    .addTransition(MenuFsmState.DATE_DIALOG, EnumSet.of(MenuFsmState.FINISH))
                    .addTransition(MenuFsmState.FINISH, EnumSet.noneOf(MenuFsmState.class))
                    .addRecogniser(MenuFsmState.MENU, new BotStartDialogRecognizer())
                    .addRecogniser(MenuFsmState.GPT_DIALOG, new GptDialogRecognizer())
                    .addRecogniser(MenuFsmState.PROFILE_DIALOG, new ProfileDialogRecognizer())
                    .addRecogniser(MenuFsmState.OPENER_DIALOG, new OpenerDialogRecognizer())
                    .addRecogniser(MenuFsmState.CHAT_DIALOG, new ChatDialogRecognizer())
                    .addRecogniser(MenuFsmState.DATE_DIALOG, new DateDialogRecognizer())
                    .addRecogniser(MenuFsmState.FINISH, new FinishRecognizer())
                    .build();
        }
    },

    BOT_MODE() {
        @Override
        public FiniteStateMachine<ModeFsmState> newInstance() {
            return FiniteStateMachine.<ModeFsmState>newBuilder()
                    .setStartState(ModeFsmState.START)
                    .setFinishState(ModeFsmState.FINISH)
                    .addTransition(ModeFsmState.START,
                            EnumSet.of(
                                    ModeFsmState.GPT_MESSAGE,
                                    ModeFsmState.PROFILE_QUESTION,
                                    ModeFsmState.OPENER_QUESTION,
                                    ModeFsmState.CHAT_MESSAGE_ADD,
                                    ModeFsmState.CHAT_MESSAGE_SEND,
                                    ModeFsmState.DATE_CELEBRITY_SELECT,
                                    ModeFsmState.DATE_CELEBRITY_MESSAGE,
                                    ModeFsmState.FINISH
                            )
                    )
                    .addTransition(ModeFsmState.GPT_MESSAGE, EnumSet.of(ModeFsmState.FINISH))
                    .addTransition(ModeFsmState.PROFILE_QUESTION, EnumSet.of(ModeFsmState.FINISH))
                    .addTransition(ModeFsmState.OPENER_QUESTION, EnumSet.of(ModeFsmState.FINISH))
                    .addTransition(ModeFsmState.CHAT_MESSAGE_ADD, EnumSet.of(ModeFsmState.FINISH))
                    .addTransition(ModeFsmState.CHAT_MESSAGE_SEND, EnumSet.of(ModeFsmState.FINISH))
                    .addTransition(ModeFsmState.DATE_CELEBRITY_SELECT, EnumSet.of(ModeFsmState.FINISH))
                    .addTransition(ModeFsmState.DATE_CELEBRITY_MESSAGE, EnumSet.of(ModeFsmState.FINISH))
                    .addTransition(ModeFsmState.FINISH, EnumSet.noneOf(ModeFsmState.class))
                    .addRecogniser(ModeFsmState.GPT_MESSAGE, new GptMessageRecognizer())
                    .addRecogniser(ModeFsmState.PROFILE_QUESTION, new ProfileQuestionRecognizer())
                    .addRecogniser(ModeFsmState.OPENER_QUESTION, new OpenerQuestionRecognizer())
                    .addRecogniser(ModeFsmState.CHAT_MESSAGE_ADD, new ChatMessageAddRecognizer())
                    .addRecogniser(ModeFsmState.CHAT_MESSAGE_SEND, new ChatMessageSendRecognizer())
                    .addRecogniser(ModeFsmState.DATE_CELEBRITY_SELECT, new DateCelebritySelectRecognizer())
                    .addRecogniser(ModeFsmState.DATE_CELEBRITY_MESSAGE, new DateCelebrityMessageRecognizer())
                    .addRecogniser(ModeFsmState.FINISH, new FinishRecognizer())
                    .build();
        }
    };

    /**
     * Returns a new instance of configured finite state machine.
     */
    public abstract FiniteStateMachine<? extends Enum> newInstance(); // NOSONAR
}
