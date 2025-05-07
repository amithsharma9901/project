package com.example.visitormanagement.service.impl;

import com.example.visitormanagement.model.Visitor;
import com.example.visitormanagement.repository.VisitorRepository;
import com.example.visitormanagement.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public Visitor addVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    @Override
    public List<Visitor> getVisitorsByUser(Long userId) {
        return visitorRepository.findByUserId(userId);
    }

    @Override
    public List<Visitor> getVisitorsByStaff(Long staffId) {
        return visitorRepository.findByStaffId(staffId);
    }
}
