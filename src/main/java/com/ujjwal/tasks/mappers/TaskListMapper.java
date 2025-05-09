package com.ujjwal.tasks.mappers;

import com.ujjwal.tasks.domain.dto.TaskListDto;
import com.ujjwal.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
