package edu.lmu.service.impl;

import edu.lmu.entity.Task;
import edu.lmu.entity.Wedding;
import edu.lmu.repository.TaskRepository;
import edu.lmu.repository.WeddingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl {

    private final TaskRepository taskRepository;

    private final WeddingRepository weddingRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Integer id) {
        return taskRepository.findById(id);
    }

    public List<Task> getTasksByWeddingId(Integer weddingId) {
        return taskRepository.findByWedding_WeddingId(weddingId);
    }

    public Task createTask(Integer weddingId, Task task) {
        Optional<Wedding> wedding = weddingRepository.findById(weddingId);
        if (wedding.isPresent()) {
            task.setWedding(wedding.get());
            return taskRepository.save(task);
        }
        return null;
    }

    public Task updateTask(Integer id, Task updatedTask) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            updatedTask.setTaskId(id);
            return taskRepository.save(updatedTask);
        }
        return null;
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
