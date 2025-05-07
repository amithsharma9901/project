package com.example.eventservice.repository;

import com.example.eventservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByCategory(String category);
}
