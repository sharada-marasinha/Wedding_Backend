package edu.lmu.service.impl;

import edu.lmu.entity.Wedding;
import edu.lmu.repository.WeddingRepository;
import edu.lmu.service.WeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeddingServiceImpl implements WeddingService {

    private final WeddingRepository weddingRepository;

    public List<Wedding> getAllWeddings() {
        return weddingRepository.findAll();
    }

    public Optional<Wedding> getWeddingById(Integer id) {
        return weddingRepository.findById(id);
    }

    public Wedding createWedding(Wedding wedding) {
        return weddingRepository.save(wedding);
    }

    public Wedding updateWedding(Integer id, Wedding updatedWedding) {
        Optional<Wedding> existingWedding = weddingRepository.findById(id);
        if (existingWedding.isPresent()) {
            updatedWedding.setWeddingId(id);
            return weddingRepository.save(updatedWedding);
        }
        return null;
    }

    public void deleteWedding(Integer id) {
        weddingRepository.deleteById(id);
    }
}