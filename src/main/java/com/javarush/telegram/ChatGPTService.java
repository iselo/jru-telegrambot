package com.javarush.telegram;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;

import javax.annotation.concurrent.Immutable;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.net.Proxy.Type.HTTP;

@Immutable
public final class ChatGPTService {

    private static final String API_OPENAI_HOST = "https://api.openai.com/";
    private static final String PROXY_HOST = "18.199.183.77";
    private static final int PROXY_PORT = 49232;

    private final ChatGPT chatGPT;

    private final List<Message> messageHistory;  //История переписки із ChatGPT; потрібна для діалогів

    private ChatGPTService(ChatGPT chatGPT) {
        this.chatGPT = chatGPT;
        this.messageHistory = new ArrayList<>();
    }

    /**
     * Одиночний запит до ChatGPT за форматом "запит"-> "відповідь".
     * Запит складається з двох частин:
     * prompt - контекст питання
     * question - власне запит
     */
    public String sendMessage(String prompt, String question) {
        Message system = Message.ofSystem(prompt);
        Message message = Message.of(question);
        messageHistory.clear();
        messageHistory.addAll(Arrays.asList(system, message));

        return sendMessagesToChatGPT();
    }

    /**
     * Запити до ChatGPT із збереженням історії повідомлень.
     * Метод setPrompt() задає контекст запиту
     */
    public void setPrompt(String prompt) {
        Message system = Message.ofSystem(prompt);
        messageHistory.clear();
        messageHistory.addAll(List.of(system));
    }

    /**
     * Запити до ChatGPT із збереженням історії повідомлень.
     * Метод addMessage() додає нове запитання (повідомлення) у чат.
     */
    public String addMessage(String question) {
        Message message = Message.of(question);
        messageHistory.add(message);

        return sendMessagesToChatGPT();
    }

    /**
     * Відправляємо ChatGPT серію повідомлень: prompt, message1, answer1, message2, answer2, ..., messageN
     * Відповідь ChatGPT додається в кінець messageHistory для подальшого використання
     */
    private String sendMessagesToChatGPT() {
        ChatCompletion chatCompletion = ChatCompletion.builder()
                .model(ChatCompletion.Model.GPT4oMini) // GPT4oMini or GPT_3_5_TURBO
                .messages(messageHistory)
                .maxTokens(3000)
                .temperature(0.9)
                .build();

        ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
        Message res = response.getChoices().get(0).getMessage();
        messageHistory.add(res);

        return res.getContent();
    }

    /**
     * Returns a new configured ChatGPTService instance.
     *
     * @return {@code ChatGPTService} instance.
     */
    public static ChatGPTService of(String token) {
        Proxy proxy = new Proxy(HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));

        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey(token)
                .apiHost(API_OPENAI_HOST)
                .proxy(proxy)
                .build()
                .init();

        return new ChatGPTService(chatGPT);
    }
}
