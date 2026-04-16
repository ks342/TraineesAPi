package com.example.traineeAPI.services;

import com.example.traineeAPI.entities.Trainee;
import java.util.List;

public interface ITraineeService {

    List<Trainee> getAllTrainees();
    List<Trainee> getTraineeByName(String name);
    Trainee getTraineeById(int id);
    Trainee addTrainee(Trainee t);
    List<Trainee> getTraineeByLocation(String location);
    void deleteTrainee(int id);
    Trainee updateTrainee(int id, Trainee t);
}