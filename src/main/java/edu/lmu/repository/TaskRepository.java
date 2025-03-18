package edu.lmu.repository;

import edu.lmu.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByWedding_WeddingId(Integer weddingId);
}
