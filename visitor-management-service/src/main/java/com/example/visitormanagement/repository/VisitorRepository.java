package com.example.visitormanagement.repository;

import com.example.visitormanagement.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    List<Visitor> findByUserId(Long userId);
    List<Visitor> findByStaffId(Long staffId);
}
