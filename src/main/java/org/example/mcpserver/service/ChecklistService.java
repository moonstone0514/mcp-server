package org.example.mcpserver.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mcpserver.entity.Checklist;
import org.example.mcpserver.entity.IsmsChecklist;
import org.example.mcpserver.repository.ChecklistRepository;
import org.example.mcpserver.repository.IsmsChecklistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistService {

    private final ChecklistRepository checklistRepository;
    private final IsmsChecklistRepository ismsChecklistRepository;
    private final OllamaService ollamaService;
    private final ObjectMapper mapper = new ObjectMapper();

    public ChecklistService(ChecklistRepository checklistRepository,
                            IsmsChecklistRepository ismsChecklistRepository,
                            OllamaService ollamaService) {
        this.checklistRepository = checklistRepository;
        this.ismsChecklistRepository = ismsChecklistRepository;
        this.ollamaService = ollamaService;
    }

    // Checklist
    public List<Checklist> getAll() {
        return checklistRepository.findAll();
    }

    public Checklist save(Checklist checklist) {
        return checklistRepository.save(checklist);
    }

    // ISMS Checklist 분석 (Ollama 연동)
    public String analyzeIsmsChecklist(Long id) throws Exception {
        IsmsChecklist entity = ismsChecklistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No ISMS Checklist with id=" + id));

        JsonNode json = mapper.valueToTree(entity);
        return ollamaService.analyze(json);
    }
}
