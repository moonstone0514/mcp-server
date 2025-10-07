package org.example.mcpserver.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OllamaService {

    private final RestTemplate rest;
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${ollama.url:http://127.0.0.1:11434/api/generate}")
    private String ollamaUrl;

    public OllamaService(RestTemplate restTemplate) {
        this.rest = restTemplate;
    }

    /**
     * Ollama에 분석 요청을 보내고 결과(텍스트)를 반환합니다.
     * - 입력 JsonNode는 안전을 위해 필요한 경우 마스킹/선별해서 보내세요.
     */
    public String analyze(JsonNode json) throws Exception {
        // 간단 마스킹(예시) - 실제로는 PII 규칙을 더 정교하게
        JsonNode sanitized = sanitize(json);

        String prompt = buildPrompt(sanitized);

        Map<String, Object> body = Map.of(
                "model", "llama2:latest",   // 시스템에 설치된 모델 이름 사용
                "prompt", prompt,
                "max_tokens", 1024,
                "temperature", 0.2
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(body), headers);

        ResponseEntity<String> resp = rest.exchange(ollamaUrl, HttpMethod.POST, request, String.class);

        if (!resp.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Ollama API error: " + resp.getStatusCodeValue() + " - " + resp.getBody());
        }
        return resp.getBody();
    }

    private JsonNode sanitize(JsonNode src) {
        // 예시: email 필드가 있으면 마스킹
        try {
            if (src.isObject() && src.has("email")) {
                ((com.fasterxml.jackson.databind.node.ObjectNode) src).put("email", "REDACTED");
            }
        } catch (Exception ignored) {}
        return src;
    }

    private String buildPrompt(JsonNode sanitizedJson) {
        StringBuilder sb = new StringBuilder();
        sb.append("You are an ISMS-P security auditor.\n");
        sb.append("Analyze the following MCP/ISMS checklist JSON and produce a structured short report with sections:\n");
        sb.append("[Summary]\n[Vulnerabilities]\n[Risks]\n[Recommended Fixes with priority: HIGH/MEDIUM/LOW]\n[Notes]\n\n");
        sb.append("Only consider ISMS-P relevant items such as access control, logging & retention, integrity, backup, authentication, encryption, firewall configuration, and vulnerability management.\n\n");
        sb.append("MCP JSON:\n");
        sb.append(sanitizedJson.toPrettyString());
        sb.append("\n\nPlease return the report as plain text with the section headings exactly as above.\n");
        return sb.toString();
    }
}
