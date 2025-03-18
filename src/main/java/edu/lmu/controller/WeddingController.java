package edu.lmu.controller;

import edu.lmu.entity.Wedding;
import edu.lmu.service.WeddingService;
import edu.lmu.service.impl.WeddingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/weddings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WeddingController {

    private final WeddingService weddingService;

    @GetMapping
    public ResponseEntity<List<Wedding>> getAllWeddings() {
        return new ResponseEntity<>(weddingService.getAllWeddings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wedding> getWeddingById(@PathVariable Integer id) {
        Optional<Wedding> wedding = weddingService.getWeddingById(id);
        return wedding.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Wedding> createWedding(@RequestBody Wedding wedding) {
        Wedding savedWedding = weddingService.createWedding(wedding);
        return new ResponseEntity<>(savedWedding, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wedding> updateWedding(@PathVariable Integer id, @RequestBody Wedding updatedWedding) {
        Wedding updated = weddingService.updateWedding(id, updatedWedding);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWedding(@PathVariable Integer id) {
        weddingService.deleteWedding(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



