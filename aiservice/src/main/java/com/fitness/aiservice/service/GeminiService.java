package com.fitness.aiservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiAPIUrl;

    @Value("${gemini.api.key}")
    private String geminiAPIKey;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = WebClient.builder().build();
    }

    public String getAnswer(String prompt) {

        Map<String, Object> requestBody = Map.of("contents", new Object[] {
                Map.of("parts", new Object[] {
                        Map.of("text", prompt)
                })
        });

        String response = webClient.post()
                .uri(geminiAPIUrl)
                .header("Content-Type", "application/json")
                .header("X-goog-api-key", geminiAPIKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;

    }

}
