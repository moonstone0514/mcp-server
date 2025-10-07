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

    @PostMapping("/isms")
    public ResponseEntity<String> receiveIsmsChecklist(@RequestBody IsmsChecklistPayload payload) {
        // TODO: Service에 넘기거나 DB 저장 로직 추가
        return ResponseEntity.ok("Received ISMS Checklist for system: " + payload.getSystem());
    }

}
