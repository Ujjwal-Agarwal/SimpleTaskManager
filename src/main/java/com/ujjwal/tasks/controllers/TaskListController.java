package com.ujjwal.tasks.controllers;

import com.ujjwal.tasks.domain.dto.TaskListDto;
import com.ujjwal.tasks.domain.entities.TaskList;
import com.ujjwal.tasks.mappers.TaskListMapper;
import com.ujjwal.tasks.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping(path="/task-lists")
public class TaskListController {

    private TaskListService taskListService;
    private TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        // 1. Get task list
        // 2. convert lists to Dto
        List<TaskList> result = taskListService.listTaskLists();

        return result.stream().map(taskListMapper::toDto).toList();
    }
    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList =  taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);
    }

    @GetMapping("/{task_list_id}")
    public Optional<TaskListDto> getTaskListById(@PathVariable("task_list_id") UUID id) {
        return taskListService.getTaskListById(id).map(taskListMapper::toDto);
    }
    @PutMapping("/{task_list_id}")
    public TaskListDto updateTaskList(@PathVariable("task_list_id") UUID id, @RequestBody TaskListDto taskListDto) {
        TaskList updatedTaskList = taskListService.updateTaskList(id,taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(updatedTaskList);
    }
}
