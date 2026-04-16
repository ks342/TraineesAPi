package com.example.traineeAPI.services;

import com.example.traineeAPI.entities.Trainee;
import com.example.traineeAPI.repositories.ITraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeServiceImpl implements ITraineeService {

    @Autowired
    private ITraineeRepository repo;

    @Override
    public List<Trainee> getAllTrainees() {
        return repo.findAll();
    }

    @Override
    public List<Trainee> getTraineeByName(String name) {
        return repo.findByTraineeName(name);
    }
    
    @Override
    public List<Trainee> getTraineeByLocation(String location){
    	return repo.findByTraineeLocation(location);
    }

    @Override
    public Trainee getTraineeById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Trainee addTrainee(Trainee t) {
        return repo.save(t);
    }

    @Override
    public void deleteTrainee(int id) {
        repo.deleteById(id);
    }
    
    @Override
    public Trainee updateTrainee(int id, Trainee t) {
        Trainee existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setTraineeName(t.getTraineeName());
            existing.setTraineeDomain(t.getTraineeDomain());
            existing.setTraineeLocation(t.getTraineeLocation());
            return repo.save(existing);
        }
        return null;
    }
}