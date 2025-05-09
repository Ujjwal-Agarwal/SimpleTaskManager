package com.ujjwal.tasks.services;

import com.ujjwal.tasks.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listTaskLists();
}
