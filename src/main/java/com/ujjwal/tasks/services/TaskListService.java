package com.ujjwal.tasks.services;

import com.ujjwal.tasks.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskListById(UUID id);
    TaskList updateTaskList(UUID taskListId,TaskList taskList);
    void deleteTaskListById(UUID id);
}
