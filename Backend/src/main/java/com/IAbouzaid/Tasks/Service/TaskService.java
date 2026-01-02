package com.IAbouzaid.Tasks.Service;

import com.IAbouzaid.Tasks.Dto.TaskDto;
import com.IAbouzaid.Tasks.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> getAllTask(UUID taskListId);
    Task createTask(UUID TaskListId,Task task);
    Optional<Task> getTask(UUID taskListId, UUID taskId);
    Task updateTask (UUID taskListID,UUID taskId,Task task);
    void deleteTask (UUID taskListID,UUID taskId);
}
