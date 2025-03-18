package edu.lmu.service;

import edu.lmu.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();

    Optional<Task> getTaskById(Integer id);

    List<Task> getTasksByWeddingId(Integer weddingId);

    Task createTask(Integer weddingId, Task task);

    Task updateTask(Integer id, Task updatedTask);

    void deleteTask(Integer id);
}
