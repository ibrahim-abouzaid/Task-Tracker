package com.IAbouzaid.Tasks.Mapper.impl;

import com.IAbouzaid.Tasks.Dto.TaskDto;
import com.IAbouzaid.Tasks.Mapper.TaskMapper;
import com.IAbouzaid.Tasks.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {


    @Override
    public TaskDto toDto(Task entity) {
        return new TaskDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDueDate(),
                entity.getPriority(),
                entity.getStatus()
        );
    }

    @Override
    public Task toEntity(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );
    }
}
