package com.ujjwal.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {

    public static void main(String[] args) {
        System.out.println("TaskListServiceImpl listTaskLists");
        SpringApplication.run(TasksApplication.class, args);
    }

}
