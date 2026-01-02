package com.IAbouzaid.Tasks.Service.impl;

import com.IAbouzaid.Tasks.Dto.TaskDto;
import com.IAbouzaid.Tasks.Repositories.TaskListRepo;
import com.IAbouzaid.Tasks.Repositories.TaskRepo;
import com.IAbouzaid.Tasks.Service.TaskService;
import com.IAbouzaid.Tasks.model.Task;
import com.IAbouzaid.Tasks.model.TaskList;
import com.IAbouzaid.Tasks.model.TaskPriority;
import com.IAbouzaid.Tasks.model.TaskStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final TaskListRepo  taskListRepo;

    public TaskServiceImpl(TaskRepo taskRepo, TaskListRepo taskListRepo) {
        this.taskRepo = taskRepo;
        this.taskListRepo = taskListRepo;
    }

    @Override
    public List<Task> getAllTask(UUID taskListId) {
        return taskRepo.findByTaskListId(taskListId);
    }

    @Transactional

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(task.getId()!=null){
            throw new IllegalArgumentException("Task Must have no ID");
        }
        if(task.getTitle()==null || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task title must not be Empty");
        }
        TaskPriority taskPriority= Optional.ofNullable(task.getPriority())
                    .orElse(TaskPriority.MEDIUM);

        TaskStatus status=TaskStatus.OPEN;

        TaskList taskList=taskListRepo.findById(taskListId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid Task List ID provided!!"));

        Task taskToSave=new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                status,
                taskPriority,
                taskList,
                LocalDate.now(),
                LocalDate.now()
        );
        return taskRepo.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepo.findByTaskListIdAndId(taskListId,taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListID, UUID taskId, Task task) {
        if(task.getId()==null){
            throw new IllegalArgumentException("Task must have an ID");
        }
        if(!Objects.equals(taskId,task.getId())){
            throw new IllegalArgumentException("Task IDs do not match");
        }
        if(task.getPriority()==null){

            throw new IllegalArgumentException("Task must have a valid priority!");
        }

        if(task.getStatus()==null){
            throw new IllegalArgumentException("Task must have a valid status");
        }
        Task existingTask=taskRepo.findByTaskListIdAndId(taskListID,taskId)
                .orElseThrow(()-> new IllegalArgumentException("Task not Found!"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdatedAt(LocalDate.now());
        return taskRepo.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListID, UUID taskId) {
        taskRepo.deleteByTaskListIdAndId(taskListID,taskId);
    }


}
