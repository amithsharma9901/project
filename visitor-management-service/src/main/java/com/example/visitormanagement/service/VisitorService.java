package com.example.visitormanagement.service;

import com.example.visitormanagement.model.Visitor;
import java.util.List;

public interface VisitorService {
    Visitor addVisitor(Visitor visitor);
    List<Visitor> getVisitorsByUser(Long userId);
    List<Visitor> getVisitorsByStaff(Long staffId);
}
