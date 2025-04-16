package com.leeyaonan.magic.demo.deepseek;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeepSeekChatDemo {

    // const
    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions";
    public static final String MODEL_R1 = "deepseek-chat";
    public static final String MODEL_V3 = "deepseek-reasoner";
    private static final List<Message> chatHistory = new ArrayList<>();

    // config
    private static final String API_KEY = ""; // 填写自己的Key，不要泄露出去
    public static final String USE_MODEL = MODEL_V3;
    public static final Integer MAX_TOKENS = 500;
    public static final int MAX_HISTORY = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("You: ");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Goodbye!");
                    break;
                }

                // 添加用户消息到历史
                addMessage("user", input);

                System.out.println("Thinking...");
                long startTime = System.currentTimeMillis();

                // 获取AI回复
                String response = getChatResponse();
                System.out.println("AI: " + response);

                System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + "ms");

                // 添加AI回复到历史
                addMessage("assistant", response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static String getChatResponse() throws Exception {
        // 构建请求体（携带完整对话历史）
        ChatRequest request = ChatRequest.builder()
                .model(USE_MODEL)
                .messages(chatHistory.toArray(new Message[0]))
                .max_tokens(MAX_TOKENS)
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(JSON.toJSONString(request)))
                .build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ChatResponse resp = JSONObject.parseObject(response.body(), ChatResponse.class);
            return resp.choices[0].message.content;
        } else {
            throw new RuntimeException("Request failed: " + response.body());
        }
    }

    private static void addMessage(String role, String content) {
        chatHistory.add(new Message(role, content));
        trimHistory();
    }


    // 保持历史不超过限制（防止超出token限制）
    private static void trimHistory() {
        // 根据实际情况设置最大保留消息数
        while (chatHistory.size() > MAX_HISTORY) {
            chatHistory.remove(0); // 移除最早的对话
        }
    }

    // 完整的请求体类定义
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class ChatRequest {
        public String model;
        public Message[] messages;
        public int max_tokens;
        public double temperature = 0.7;  // 可选参数
    }

    // 完整的消息类定义
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class Message {
        public String role;  // user/assistant/system
        public String content;
    }

    // 完整的响应体类定义
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class ChatResponse {
        public String id;
        public String object;
        public String model;
        public Choice[] choices;
        public Usage usage;  // 新增用量统计字段
        public String system_fingerprint;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class Choice {
        public Message message;
        public int index;
        public String finish_reason;
        public String logprobs;
    }

    // 新增用量统计类
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class Usage {
        public int prompt_tokens;
        public int completion_tokens;
        public int total_tokens;
        public int prompt_cache_hit_tokens;
        public int prompt_cache_miss_tokens;
    }
}
