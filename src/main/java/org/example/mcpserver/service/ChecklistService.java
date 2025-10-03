package org.example.mcpserver.service;

import org.example.mcpserver.entity.Checklist;
import org.example.mcpserver.repository.ChecklistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistService {

    private final ChecklistRepository checklistRepository;

    public ChecklistService(ChecklistRepository checklistRepository) {
        this.checklistRepository = checklistRepository;
    }

    public List<Checklist> getAll() {
        return checklistRepository.findAll();
    }

    public Checklist save(Checklist checklist) {
        return checklistRepository.save(checklist);
    }
}
