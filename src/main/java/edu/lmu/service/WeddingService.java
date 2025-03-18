package edu.lmu.service;

import edu.lmu.entity.Wedding;

import java.util.List;
import java.util.Optional;

public interface WeddingService {
     List<Wedding> getAllWeddings();
     Optional<Wedding> getWeddingById(Integer id);
     Wedding createWedding(Wedding wedding);
     Wedding updateWedding(Integer id, Wedding updatedWedding);
     void deleteWedding(Integer id);
}
