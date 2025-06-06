package com.ujjwal.tasks.services.impl;

import com.ujjwal.tasks.domain.entities.TaskList;
import com.ujjwal.tasks.repositories.TaskListRepository;
import com.ujjwal.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
//        System.out.println("TaskListServiceImpl listTaskLists");
        return taskListRepository.findAll();
        // JpaRepository findAll returns a List
        // CrudRepository findall returns a Iterable
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null!=taskList.getId()){
            throw new IllegalArgumentException("Task List already has an ID");
        }
        if(null==taskList.getTitle() || taskList.getTitle().trim().equals("")){
            throw new IllegalArgumentException("Task List Title is empty");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskListById(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(null==taskList.getId()){
            throw new IllegalArgumentException("Task List does not have an ID");
        }
        if(!Objects.equals(taskList.getId(),taskListId)){
            throw new IllegalArgumentException("Task List does not have the same ID");
        }
        TaskList oldTaskList = taskListRepository.findById(taskListId).orElseThrow(
                ()->new IllegalArgumentException("Task List not found"));
        oldTaskList.setTitle(taskList.getTitle());
        oldTaskList.setDescription(taskList.getDescription());
        oldTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(oldTaskList);
    }

    @Override
    public void deleteTaskListById(UUID id) {
        taskListRepository.deleteById(id); // DeleteByID handles null cases
    }
}
