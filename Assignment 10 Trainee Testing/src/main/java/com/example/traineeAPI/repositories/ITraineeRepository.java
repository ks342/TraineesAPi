package com.example.traineeAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.traineeAPI.entities.Trainee;
import java.util.List;

public interface ITraineeRepository extends JpaRepository<Trainee, Integer> {

    List<Trainee> findByTraineeName(String traineeName);
    
    List <Trainee> findByTraineeLocation(String location);
}