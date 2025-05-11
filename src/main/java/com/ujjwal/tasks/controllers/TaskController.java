package com.ujjwal.tasks.controllers;

import com.ujjwal.tasks.domain.dto.TaskDto;
import com.ujjwal.tasks.domain.entities.Task;
import com.ujjwal.tasks.mappers.TaskMapper;
import com.ujjwal.tasks.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists/{task_list_id}/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping()
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        List<Task> result = taskService.listTasks(taskListId);
        return result.stream().map(taskMapper::toDto).toList();
    }
    @PostMapping()
    public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId,@RequestBody TaskDto taskDto) {
        Task createdTask = taskService.createTask(taskMapper.fromDto(taskDto),taskListId);
        return taskMapper.toDto(createdTask);
    }
}
