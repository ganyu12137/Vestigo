package org.example.vestigo.repository;

import org.example.vestigo.entity.TraceEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraceEventRepository extends JpaRepository<TraceEvent, Long> {
    List<TraceEvent> findByTitleContainingOrContentContaining(String title, String content);
}
