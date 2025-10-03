package org.example.mcpserver.controller;

import org.example.mcpserver.entity.Checklist;
import org.example.mcpserver.service.ChecklistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checklists")
public class ChecklistController {

    private final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @GetMapping
    public List<Checklist> getAll() {
        return checklistService.getAll();
    }

    @PostMapping
    public Checklist create(@RequestBody Checklist checklist) {
        return checklistService.save(checklist);
    }
}
