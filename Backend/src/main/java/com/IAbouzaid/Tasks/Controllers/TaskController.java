package com.IAbouzaid.Tasks.Controllers;


import com.IAbouzaid.Tasks.Dto.TaskDto;
import com.IAbouzaid.Tasks.Mapper.TaskListMapper;
import com.IAbouzaid.Tasks.Mapper.TaskMapper;
import com.IAbouzaid.Tasks.Service.TaskService;
import com.IAbouzaid.Tasks.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {


    private final TaskService taskService;
    private final TaskMapper  taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> getAllTasks(@PathVariable("task_list_id") UUID taskListID){
    return taskService.getAllTask(taskListID)
            .stream()
            .map(taskMapper::toDto)
            .toList();
    }

    @PostMapping
    public TaskDto createTask (@PathVariable("task_list_id") UUID taskListID, @RequestBody TaskDto taskDto){
        Task createdTask =taskService.createTask(taskListID,taskMapper.toEntity(taskDto));
        return taskMapper.toDto(createdTask);
    }

    @GetMapping("/{taskId}")
    public Optional<TaskDto> getTask (@PathVariable("task_list_id") UUID taskListID, @PathVariable("taskId") UUID taskId){

        return taskService.getTask(taskListID,taskId).map(taskMapper::toDto);
    }

    @PutMapping(path = "/{taskId}")
    public TaskDto updateTask(
            @PathVariable("task_list_id") UUID taskListID,
            @PathVariable("taskId") UUID taskId,
            @RequestBody Task task){
        return taskMapper.toDto(taskService.updateTask(taskListID,taskId, task));

    }

    @DeleteMapping(path = "/{taskId}")
    public void deleteTask( @PathVariable("task_list_id") UUID taskListID,
                            @PathVariable("taskId") UUID taskId){

        taskService.deleteTask(taskListID,taskId);
    }
}
