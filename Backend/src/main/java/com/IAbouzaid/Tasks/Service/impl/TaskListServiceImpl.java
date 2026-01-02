package com.IAbouzaid.Tasks.Service.impl;

import com.IAbouzaid.Tasks.Repositories.TaskListRepo;
import com.IAbouzaid.Tasks.Service.TaskListService;
import com.IAbouzaid.Tasks.model.TaskList;
import jakarta.transaction.Transactional;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private TaskListRepo taskListRepo;

    public TaskListServiceImpl(TaskListRepo taskListRepo) {
        this.taskListRepo = taskListRepo;
    }

    @Override
    public List<TaskList> getListOfTaskLists() {
        return taskListRepo.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList)  {
        if(taskList.getId() !=null){
            throw new IllegalArgumentException("Task List already has an ID");
        }
        if(taskList.getTitle()==null || taskList.getTitle().isBlank() ){
        throw new IllegalArgumentException("Task List title must have title");
        }
        LocalDate now =  LocalDate.now();
         return taskListRepo.save(new TaskList(
                    null,
                    taskList.getTitle(),
                    taskList.getDescription(),
                    null ,
                    now,
                    now
            ));



    }
    @Transactional
    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepo.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(taskList.getId()==null){
            throw new IllegalArgumentException("Task List must have an ID");
        }
        if(!Objects.equals(taskList.getId(),taskListId)){
            throw new IllegalArgumentException("Attempting to change task List ID,this is not permitted ");
        }
        TaskList existingTaskList=taskListRepo.findById(taskListId)
                                                .orElseThrow(()-> new IllegalArgumentException("Task List not Found"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdatedAt(LocalDate.now());

        return taskListRepo.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepo.deleteById(taskListId);
    }
}
