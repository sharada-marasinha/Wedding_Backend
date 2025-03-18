package edu.lmu.repository;

import edu.lmu.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

}
