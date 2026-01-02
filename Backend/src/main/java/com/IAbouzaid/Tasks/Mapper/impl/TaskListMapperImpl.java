package com.IAbouzaid.Tasks.Mapper.impl;

import com.IAbouzaid.Tasks.Dto.TaskDto;
import com.IAbouzaid.Tasks.Dto.TaskListDto;
import com.IAbouzaid.Tasks.Mapper.TaskListMapper;
import com.IAbouzaid.Tasks.Mapper.TaskMapper;
import com.IAbouzaid.Tasks.model.Task;
import com.IAbouzaid.Tasks.model.TaskList;
import com.IAbouzaid.Tasks.model.TaskStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper  taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }


    @Override
    public TaskListDto toDto(TaskList entity) {
        return new TaskListDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                getTaskListSize(entity.getTasks()),
                calculateTaskListProgress(entity.getTasks()),
                getListOfTaskDto(entity)

        );
    }
    private  Integer getTaskListSize(List<Task> tasks){

        return  Optional.ofNullable(tasks)
                .map(List::size)
                .orElse(0);
    }
    private List<TaskDto> getListOfTaskDto(TaskList taskList){

       return Optional.ofNullable(taskList.getTasks())
                .map(tasks ->tasks.stream()
                        .map(taskMapper::toDto)
                        .toList()
                ).orElse(null);
    }

    @Override
    public TaskList toEntity(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks ->tasks.stream()
                                .map(taskMapper::toEntity)
                                .toList()
                        ).orElse(null),
                null,
                null



        );
    }
    private Double calculateTaskListProgress(List<Task>tasks){
        if(tasks==null) return null;

        long closedTaskStatus = tasks.stream().
                filter(task -> task.getStatus()== TaskStatus.CLOSE)
                .count();
        return  (double)closedTaskStatus/tasks.size();
    }
}
