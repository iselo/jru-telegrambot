package com.javarush.telegram;

import com.google.common.eventbus.Subscribe;
import com.javarush.telegram.eventbus.events.ChatGPTMessageEvent;
import com.javarush.telegram.eventbus.events.ChatGPTPromptEvent;
import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.Message;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.net.Proxy.Type.HTTP;

public final class ChatGPTService {

    private static final String API_OPENAI_HOST = "https://api.openai.com/";
    private static final String PROXY_HOST = System.getenv("PROXY_HOST");
    private static final int PROXY_PORT = Integer.parseInt(System.getenv("PROXY_PORT"));

    private final ChatGPT chatGPT;
    private final List<Message> messageHistory = new ArrayList<>();

    private ChatGPTService(ChatGPT chatGPT) {
        this.chatGPT = checkNotNull(chatGPT);
    }

    /**
     * Returns a new configured ChatGPTService instance.
     *
     * @return {@code ChatGPTService} instance.
     */
    public static ChatGPTService of(String token) {
        checkNotNull(token);

        Proxy proxy = new Proxy(HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));

        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(token)
                .apiHost(API_OPENAI_HOST)
                .proxy(proxy)
                .build()
                .init();

        return new ChatGPTService(chatGPT);
    }

    /**
     * Одиночний запит до ChatGPT за форматом "запит"-> "відповідь".
     * Запит складається з двох частин:
     * prompt - контекст питання
     * question - власне запит
     */
    public String sendMessage(String prompt, String question) {
        checkNotNull(prompt);
        checkNotNull(question);

        var system = Message.ofSystem(prompt);
        var message = Message.of(question);
        messageHistory.clear();
        messageHistory.addAll(Arrays.asList(system, message));

        return sendMessagesToChatGPT();
    }

    /**
     * Sets prompt for the request to ChatGPT.
     */
    public void setPrompt(String prompt) {
        checkNotNull(prompt);

        var system = Message.ofSystem(prompt);
        messageHistory.clear();
        messageHistory.addAll(List.of(system));
    }

    /**
     * Запити до ChatGPT із збереженням історії повідомлень.
     * Метод addMessage() додає нове запитання (повідомлення) у чат.
     */
    public String addMessage(String question) {
        checkNotNull(question);

        var message = Message.of(question);
        messageHistory.add(message);
        return sendMessagesToChatGPT();
    }

    /**
     * Відправляємо ChatGPT серію повідомлень: prompt, message1, answer1, message2, answer2, ..., messageN
     * Відповідь ChatGPT додається в кінець messageHistory для подальшого використання
     */
    private String sendMessagesToChatGPT() {
        var chatCompletion = ChatCompletion.builder()
                .model(ChatCompletion.Model.GPT4oMini) // GPT4oMini or GPT_3_5_TURBO
                .messages(messageHistory)
                .maxTokens(3000)
                .temperature(0.9)
                .build();

        var chatCompletionResponse = chatGPT.chatCompletion(chatCompletion);
        var message = chatCompletionResponse.getChoices().get(0).getMessage();
        messageHistory.add(message);

        return message.getContent();
    }

    @Subscribe
    void handle(ChatGPTPromptEvent event) {
        setPrompt(event.payload().data());
    }

    @Subscribe
    void handle(ChatGPTMessageEvent event) {
        var consumer = event.consumer();
        if (consumer != null) {
            var result = addMessage(event.payload().data());
            consumer.accept(result);
        }
    }
}
