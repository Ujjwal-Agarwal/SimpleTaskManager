package com.ujjwal.tasks.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "task_lists")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskList {
    @Id
    @Column(name="id", nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "taskList",cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    // REMOVE - If we delete tasklist then tasks associated with it will be removed
    // PERSIST - If we add a new tasklist then all tasks in it are also saved
    private List<Task> tasks;

    @Column(name="created",nullable = false)
    private LocalDateTime created;

    @Column(name="updated", nullable = false)
    private LocalDateTime updated;
}
