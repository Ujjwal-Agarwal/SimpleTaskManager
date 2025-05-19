package com.ujjwal.tasks.services.impl;

import com.ujjwal.tasks.domain.entities.Task;
import com.ujjwal.tasks.domain.entities.TaskList;
import com.ujjwal.tasks.domain.entities.TaskPriority;
import com.ujjwal.tasks.domain.entities.TaskStatus;
import com.ujjwal.tasks.repositories.TaskListRepository;
import com.ujjwal.tasks.repositories.TaskRepository;
import com.ujjwal.tasks.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Override
    public Task createTask(Task task, UUID taskListId) {
        if(null!=task.getId()){throw new IllegalArgumentException("Task id already exists");}
        if(taskListId == null) throw new IllegalArgumentException("Task List ID cannot be null");
        if(task.getTitle() == null || task.getTitle().isBlank()) throw new IllegalArgumentException("Task Title cannot be null");

        TaskPriority priority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus status = TaskStatus.OPEN;
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task List Id"));

        LocalDateTime now = LocalDateTime.now();
        return taskRepository.save(new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                status,
                priority,
                taskList,
                now,
                now
        ));

    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(task.getId() == null) throw new IllegalArgumentException("Task ID cannot be null");
        if(!Objects.equals(taskId,task.getId())){
            throw new IllegalArgumentException("Task IDs do not match");
        }
        if(task.getPriority() == null) throw new IllegalArgumentException("Task Priority cannot be null");
        if(task.getStatus() == null) throw new IllegalArgumentException("Task Status cannot be null");

        Task currentTask = taskRepository.findByTaskListIdAndId(taskListId,taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task List or Task ID"));

        currentTask.setTitle(task.getTitle());
        currentTask.setDescription(task.getDescription());
        currentTask.setDueDate(task.getDueDate());
        currentTask.setPriority(task.getPriority());
        currentTask.setStatus(task.getStatus());
        currentTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(currentTask);
    }
    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId,taskId);
    }
}
