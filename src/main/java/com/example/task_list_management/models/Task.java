package com.example.task_list_management.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "date_create", nullable = false, updatable = false)
    private LocalDateTime dateCreate;

    public Task() {
        this.dateCreate = LocalDateTime.now();
    }
}
