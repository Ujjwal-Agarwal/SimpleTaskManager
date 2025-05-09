package com.ujjwal.tasks.controllers;

import com.ujjwal.tasks.domain.dto.TaskListDto;
import com.ujjwal.tasks.domain.entities.TaskList;
import com.ujjwal.tasks.mappers.TaskListMapper;
import com.ujjwal.tasks.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
}
