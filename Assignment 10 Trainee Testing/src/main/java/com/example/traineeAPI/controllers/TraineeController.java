package com.example.traineeAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.traineeAPI.entities.Trainee;
import com.example.traineeAPI.services.TraineeServiceImpl;

@RestController
@RequestMapping("/trainees")
public class TraineeController {

    @Autowired
    private TraineeServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Trainee>> fetchingAllEmployee() {
        List<Trainee> t = service.getAllTrainees();

        if (!t.isEmpty()) {
            return ResponseEntity.ok(t);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainee> getById(@PathVariable int id) {
        Trainee t = service.getTraineeById(id);

        if (t != null)
            return ResponseEntity.ok(t);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/byName")
    public ResponseEntity<List<Trainee>> getByName(@RequestParam("name") String tname) {
        List<Trainee> t = service.getTraineeByName(tname);

        if (!t.isEmpty()) {
            return ResponseEntity.ok(t);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byTrainingLocation/{location}")
    public List<Trainee> getByLocation(@PathVariable String location) {
        return service.getTraineeByLocation(location);
    }

    @PostMapping
    public Trainee add(@RequestBody Trainee t) {
        return service.addTrainee(t);
    }

    @PutMapping("/{id}")
    public Trainee update(@PathVariable int id, @RequestBody Trainee t) {
        return service.updateTrainee(id, t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteTrainee(id);
    }
}