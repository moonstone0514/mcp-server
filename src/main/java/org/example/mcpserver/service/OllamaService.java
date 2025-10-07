package org.example.mcpserver.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class OllamaService {

    private final RestTemplate restTemplate;

    public OllamaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String analyzeChecklist(String prompt) {
        String url = "http://localhost:11434/api/generate";

        // 요청 JSON payload
        Map<String, Object> request = new HashMap<>();
        request.put("model", "llama2:latest");
        request.put("prompt", prompt);
        request.put("stream", false);  // ✅ 스트리밍 끔 (최종 응답만 받음)

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        // Ollama 응답 받기
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        // Ollama의 "response" 필드 꺼내오기
        if (response.getBody() != null && response.getBody().containsKey("response")) {
            return response.getBody().get("response").toString();
        }
        return "⚠️ Ollama 응답 없음";
    }
}
