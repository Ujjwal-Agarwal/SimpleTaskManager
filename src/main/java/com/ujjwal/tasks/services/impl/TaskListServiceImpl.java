package com.ujjwal.tasks.services.impl;

import com.ujjwal.tasks.domain.entities.TaskList;
import com.ujjwal.tasks.repositories.TaskListRepository;
import com.ujjwal.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        System.out.println("TaskListServiceImpl listTaskLists");
        return taskListRepository.findAll();
        // JpaRepository findAll returns a List
        // CrudRepository findall returns a Iterable
    }
}
