package org.example.mcpserver.repository;

import org.example.mcpserver.entity.IsmsChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsmsChecklistRepository extends JpaRepository<IsmsChecklist, Long> {
}
