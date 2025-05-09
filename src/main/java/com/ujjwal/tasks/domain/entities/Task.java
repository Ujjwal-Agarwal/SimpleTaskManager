package com.ujjwal.tasks.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name="description")
    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "status", nullable = false)
    private TaskStatus status;

    @Column(name="priority",nullable = false)
    private TaskPriority priority;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY - task lsit won't be loaded until it's needed
    @JoinColumn(name = "task_list_id") // Foreign key column
    private TaskList taskList;

    @Column(name = "created",nullable = false)
    private LocalDateTime created;

    @Column(name="updated",nullable = false)
    private LocalDateTime updated;

//    public Task() {
//    }
//
//    public Task(UUID id, String title, String description, LocalDateTime dueData, TaskStatus status, TaskPriority priority, LocalDateTime created, LocalDateTime updated) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.dueData = dueData;
//        this.status = status;
//        this.priority = priority;
//        this.created = created;
//        this.updated = updated;
//    }
//
//    public UUID getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public LocalDateTime getDueData() {
//        return dueData;
//    }
//
//    public TaskStatus getStatus() {
//        return status;
//    }
//
//    public TaskPriority getPriority() {
//        return priority;
//    }
//
//    public LocalDateTime getCreated() {
//        return created;
//    }
//
//    public LocalDateTime getUpdated() {
//        return updated;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setDueData(LocalDateTime dueData) {
//        this.dueData = dueData;
//    }
//
//    public void setStatus(TaskStatus status) {
//        this.status = status;
//    }
//
//    public void setPriority(TaskPriority priority) {
//        this.priority = priority;
//    }
//
//    public void setCreated(LocalDateTime created) {
//        this.created = created;
//    }
//
//    public void setUpdated(LocalDateTime updated) {
//        this.updated = updated;
//    }
}
