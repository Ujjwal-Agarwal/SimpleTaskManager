package com.ujjwal.tasks.mappers;

import com.ujjwal.tasks.domain.dto.TaskDto;
import com.ujjwal.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
