package org.example.vestigo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "trace_event")
public class TraceEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private LocalDateTime eventTime;
    private String device;
    private String source;
    private String title;



    @Column(columnDefinition = "text")

        private String content;
        private String project;


}
