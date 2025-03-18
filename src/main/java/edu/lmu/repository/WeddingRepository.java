package edu.lmu.repository;

import edu.lmu.entity.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface WeddingRepository extends JpaRepository<Wedding, Integer> {
    // Custom query methods can be added here
}
