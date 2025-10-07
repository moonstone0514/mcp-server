package org.example.mcpserver.controller;

import org.example.mcpserver.entity.Checklist;
import org.example.mcpserver.entity.IsmsChecklist;
import org.example.mcpserver.dto.IsmsChecklistPayload;
import org.example.mcpserver.service.ChecklistService;
import org.example.mcpserver.repository.IsmsChecklistRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checklists")
public class ChecklistController {

    private final ChecklistService checklistService;
    private final IsmsChecklistRepository ismsChecklistRepository;

    public ChecklistController(ChecklistService checklistService,
                               IsmsChecklistRepository ismsChecklistRepository) {
        this.checklistService = checklistService;
        this.ismsChecklistRepository = ismsChecklistRepository;
    }

    // ✅ 기존 Checklist 전체 조회
    @GetMapping
    public List<Checklist> getAll() {
        return checklistService.getAll();
    }

    // ✅ 기존 Checklist 생성
    @PostMapping
    public Checklist create(@RequestBody Checklist checklist) {
        return checklistService.save(checklist);
    }

    // ✅ ISMS 체크리스트 저장
    @PostMapping("/isms")
    public ResponseEntity<String> receiveIsmsChecklist(@RequestBody IsmsChecklistPayload payload) {
        IsmsChecklist entity = new IsmsChecklist();
        entity.setSystem(payload.getSystem());
        entity.setTimestamp(payload.getTimestamp());
        entity.setUserCount(payload.getUserCount());
        entity.setLastPasswordChange(payload.getLastPasswordChange());
        entity.setLogRetentionDays(payload.getLogRetentionDays());
        entity.setLogIntegrity(payload.getLogIntegrity());
        entity.setFirewallStatus(payload.getFirewallStatus());
        entity.setMfaEnabled(payload.getMfaEnabled());

        ismsChecklistRepository.save(entity);

        return ResponseEntity.ok("✅ Saved ISMS Checklist for system: " + payload.getSystem());
    }

    // ✅ ISMS 체크리스트 전체 조회
    @GetMapping("/isms")
    public List<IsmsChecklist> getAllIsmsChecklists() {
        return ismsChecklistRepository.findAll();
    }
}
