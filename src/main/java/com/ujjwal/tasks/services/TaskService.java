package com.ujjwal.tasks.services;

import com.ujjwal.tasks.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
    Task createTask(Task task, UUID taskListId);
}
