package com.IAbouzaid.Tasks.Service;

import com.IAbouzaid.Tasks.model.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> getListOfTaskLists();
    TaskList createTaskList(TaskList taskList) ;
    Optional<TaskList> getTaskList(UUID id);
    TaskList updateTaskList (UUID taskListId,TaskList  taskList);
    void deleteTaskList(UUID taskListId);
}
