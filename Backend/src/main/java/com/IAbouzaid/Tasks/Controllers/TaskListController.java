package com.IAbouzaid.Tasks.Controllers;

import com.IAbouzaid.Tasks.Dto.TaskDto;
import com.IAbouzaid.Tasks.Dto.TaskListDto;
import com.IAbouzaid.Tasks.Mapper.TaskListMapper;
import com.IAbouzaid.Tasks.Repositories.TaskListRepo;
import com.IAbouzaid.Tasks.Service.TaskListService;
import com.IAbouzaid.Tasks.model.TaskList;
import com.IAbouzaid.Tasks.model.TaskStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> getAllTaskLists() {
        return taskListService.getListOfTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .map(this::calculateProgress)
                .toList();
    }
    private TaskListDto calculateProgress(TaskListDto dto) {
        if (dto.tasks() == null || dto.tasks().isEmpty()) {
            return new TaskListDto(
                    dto.id(),
                    dto.title(),
                    dto.description(),
                    0,
                    0.0,
                    dto.tasks()
            );
        }

        long closed = dto.tasks().stream()
                .filter(task -> task.status() == TaskStatus.CLOSE)
                .count();

        int taskNumber=dto.tasks().size();
        int progress = (int) Math.round(
                (double) closed / dto.tasks().size() * 100
        );

        return new TaskListDto(
                dto.id(),
                dto.title(),
                dto.description(),
                taskNumber,
                (double)progress,
                dto.tasks()

        );
    }
    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
        TaskList creatadTaskList =taskListService.createTaskList(
                taskListMapper.toEntity(taskListDto));

        return taskListMapper.toDto(creatadTaskList);
    }

    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId){

        return taskListService.getTaskList(taskListId).map(taskListMapper::toDto).map(this::calculateProgress);

    }

    @PutMapping(path = "/{task_list_id}")
    public TaskListDto updateTaskList(@PathVariable("task_list_id") UUID taskListId,@RequestBody TaskListDto taskListDto){

        TaskList updateTaskList=taskListService.updateTaskList(taskListId,taskListMapper.toEntity( taskListDto));

        return taskListMapper.toDto(updateTaskList);
    }

    @DeleteMapping(path = "/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId){
        taskListService.deleteTaskList(taskListId);
    }



}
