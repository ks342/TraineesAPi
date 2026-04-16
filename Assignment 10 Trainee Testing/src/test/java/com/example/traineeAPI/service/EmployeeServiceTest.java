package com.example.traineeAPI.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.traineeAPI.entities.Trainee;
import com.example.traineeAPI.repositories.ITraineeRepository;
import com.example.traineeAPI.services.TraineeServiceImpl;

// ✅ ONLY MockitoExtension — NO @SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    ITraineeRepository traineeRepo;

    // ✅ NO @Autowired — only @InjectMocks
    @InjectMocks
    TraineeServiceImpl traineeService;

    // -------------------- getTraineeById --------------------

    @Test
    void testFindTraineeById() {
        Trainee t = new Trainee();
        Mockito.when(traineeRepo.findById(7)).thenReturn(Optional.of(t));

        Trainee result = traineeService.getTraineeById(7);

        assertNotNull(result);
        Mockito.verify(traineeRepo, Mockito.times(1)).findById(7);
    }

    @Test
    void testFindTraineeByIdNotFound() {
        Mockito.when(traineeRepo.findById(10)).thenReturn(Optional.empty());

        Trainee result = traineeService.getTraineeById(10);

        assertNull(result);
        Mockito.verify(traineeRepo, Mockito.times(1)).findById(10);
    }

    // -------------------- getAllTrainees --------------------

    @Test
    void testGetAllTrainees() {
        List<Trainee> list = Arrays.asList(new Trainee(), new Trainee());
        Mockito.when(traineeRepo.findAll()).thenReturn(list);

        List<Trainee> result = traineeService.getAllTrainees();

        assertEquals(2, result.size());
        Mockito.verify(traineeRepo, Mockito.times(1)).findAll();
    }

    // -------------------- findByTraineeName --------------------

    @Test
    void testFindByTraineeName() {
        List<Trainee> list = Arrays.asList(new Trainee());
        Mockito.when(traineeRepo.findByTraineeName("aman")).thenReturn(list);

        List<Trainee> result = traineeService.getTraineeByName("aman");

        assertNotNull(result);
        assertEquals(1, result.size());
        Mockito.verify(traineeRepo, Mockito.times(1)).findByTraineeName("aman");
    }

    // -------------------- insertTrainee --------------------

    @Test
    void testInsertTrainee() {
        Trainee t = new Trainee();
        t.setTraineeName("Aman");
        Mockito.when(traineeRepo.save(t)).thenReturn(t);

        Trainee result = traineeService.addTrainee(t);

        assertNotNull(result);
        assertEquals("Aman", result.getTraineeName());
        Mockito.verify(traineeRepo, Mockito.times(1)).save(t);
    }

    // -------------------- updateTrainee --------------------

    @Test
    void testUpdateTraineeSuccess() {
        Trainee existing = new Trainee();
        existing.setTraineeName("Old");

        Trainee updated = new Trainee();
        updated.setTraineeName("New");
        updated.setTraineeDomain("IT");
        updated.setTraineeLocation("Delhi");

        Mockito.when(traineeRepo.findById(1)).thenReturn(Optional.of(existing));
        Mockito.when(traineeRepo.save(existing)).thenReturn(existing);

        Trainee result = traineeService.updateTrainee(1, updated);

        assertNotNull(result);
        assertEquals("New", result.getTraineeName());
        assertEquals("IT", result.getTraineeDomain());
        assertEquals("Delhi", result.getTraineeLocation());
        Mockito.verify(traineeRepo).findById(1);
        Mockito.verify(traineeRepo).save(existing);
    }

    @Test
    void testUpdateTraineeNotFound() {
        Trainee updated = new Trainee();
        Mockito.when(traineeRepo.findById(1)).thenReturn(Optional.empty());

        Trainee result = traineeService.updateTrainee(1, updated);

        assertNull(result);
        Mockito.verify(traineeRepo).findById(1);
    }

    // -------------------- deleteTrainee --------------------

    @Test
    void testFindTraineeByIdForNoException() {
        Mockito.when(traineeRepo.findById(7)).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> traineeService.getTraineeById(7));
        Mockito.verify(traineeRepo, Mockito.times(1)).findById(7);
    }
}
