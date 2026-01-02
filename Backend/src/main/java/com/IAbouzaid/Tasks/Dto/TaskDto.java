package com.IAbouzaid.Tasks.Dto;

import com.IAbouzaid.Tasks.model.TaskPriority;
import com.IAbouzaid.Tasks.model.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
         LocalDate dueDate,
        TaskPriority priority,
        TaskStatus status

) {
}
