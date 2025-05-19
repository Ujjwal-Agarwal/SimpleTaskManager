package com.ujjwal.tasks.services;

import com.ujjwal.tasks.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
    Task createTask(Task task, UUID taskListId);
    Optional<Task> getTask(UUID taskListId, UUID taskId);
    Task updateTask(UUID taskListId, UUID taskId,Task task);
    void deleteTask(UUID taskListId, UUID taskId);
}
