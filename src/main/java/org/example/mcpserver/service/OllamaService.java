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
    private final RestTemplate rest = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${ollama.url:http://127.0.0.1:11434/api/generate}")
    private String ollamaUrl;

    public String analyze(JsonNode json) throws Exception {
        String prompt = """
            You are an ISMS-P security auditor.
            Analyze the following MCP JSON and provide:
            [Summary], [Vulnerabilities], [Risks], [Recommended Fixes with priority], [Notes].
            JSON:
            """ + json.toPrettyString();

        Map<String, Object> body = Map.of(
                "model", "llama2",
                "prompt", prompt,
                "max_tokens", 512
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(body), headers);
        ResponseEntity<String> response = rest.exchange(ollamaUrl, HttpMethod.POST, request, String.class);

        return response.getBody();
    }
}
