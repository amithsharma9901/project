package com.example.sos.repository;

import com.example.sos.model.SosAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SOSAlertRepository extends JpaRepository<SosAlert, Long> {
   // List<SosAlert> findByStatus(String status);
    List<SosAlert> findByAcknowledgedFalse();
    List<SosAlert> findByUserId(Long userId);
    }