package com.ujjwal.tasks.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "task_lists")
public class TaskList {
    @Id
    @Column(name="id", nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name="created",nullable = false)
    private LocalDateTime created;

    @Column(name="updated", nullable = false)
    private LocalDateTime updated;
}
