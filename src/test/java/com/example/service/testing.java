package com.example.service;

import com.example.DTO.WorkoutDTO;
import com.example.Entity.Goal;
import com.example.Entity.User;
import com.example.Entity.Workout;
import com.example.Repository.UserRepository;
import com.example.Repository.WorkoutRepository;
import com.example.Service.Goalservice;
import com.example.Service.WorkoutService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkoutServiceTest {

    @InjectMocks
    private WorkoutService workoutService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WorkoutRepository workoutRepository;

    @Mock
    private Goalservice goalService;  // Mock GoalService

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveWorkoutLog() {
        // Arrange
        long userId = 1L;

        User user = new User();
        user.setId(userId);
        
        WorkoutDTO workoutDTO = new WorkoutDTO();
        workoutDTO.setUserid(userId);
        workoutDTO.setExerciseType("running");
        workoutDTO.setDuration(30);  // Duration in minutes
        workoutDTO.setIntencity("high");
        workoutDTO.setWorkoutDate(LocalDate.now());

        Workout workoutToSave = new Workout();
        workoutToSave.setExserciseType(workoutDTO.getExerciseType());
        workoutToSave.setDuration(workoutDTO.getDuration());
        workoutToSave.setCalories(300);  // Example calculated calories
        workoutToSave.setIntencity(workoutDTO.getIntencity());
        workoutToSave.setWorkoutDate(workoutDTO.getWorkoutDate());
        workoutToSave.setUser(user);

        Goal goal = new Goal();
        goal.setEndDate(LocalDate.now().plusDays(1));  // Goal not expired
        goal.setProgressCalories(0);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(workoutRepository.save(any(Workout.class))).thenReturn(workoutToSave);
        when(goalService.getGoalByUserId(userId)).thenReturn(Arrays.asList(goal));

        // Act
        Workout savedWorkout = workoutService.saveworkoutLog(workoutDTO);

        // Assert
        assertNotNull(savedWorkout);
        assertEquals(workoutToSave.getExserciseType(), savedWorkout.getExserciseType());
        assertEquals(300, savedWorkout.getCalories());
        assertEquals(user, savedWorkout.getUser());
        
        // Verify that the goal's progress is updated
        assertEquals(300, goal.getProgressCalories());
        verify(goalService, times(1)).updateGoal(goal);
    }

    @Test
    void getByUserId()
    {
        long userid=21;

        User user=new User();
        user.setId(userid);
        user.setName("ganesh lakade");

        Workout workout=new Workout();
        workout.setExserciseType("running");
        workout.setDuration(30);
        workout.setCalories(300);
        workout.setIntencity("mid");
        workout.setWorkoutDate(LocalDate.now());
        workout.setUser(user);

        Workout workout1=new Workout();
        workout1.setExserciseType("cycling");
        workout1.setDuration(40);
        workout1.setCalories(400);
        workout1.setIntencity("high");
        workout1.setWorkoutDate(LocalDate.now().minusDays(1));
        workout1.setUser(user);

        List<Workout> workoutList= Arrays.asList(workout,workout1);

        when(workoutRepository.findByUserId(userid)).thenReturn(workoutList);

        List<Workout> getworkoutbyuser = workoutService.getworkoutbyuser(userid);

        assertEquals(workoutList.size(),getworkoutbyuser.size());
        assertEquals(workoutList.get(0).getExserciseType(),getworkoutbyuser.get(0).getExserciseType());
        assertEquals(workoutList.get(1).getExserciseType(),getworkoutbyuser.get(1).getExserciseType());
        verify(workoutRepository,times(1)).findByUserId(userid);
    }

    @Test
    void Updateworkout()
    {
        long workoutId=12;

        Workout exWorkout=new Workout();
        exWorkout.setId(workoutId);
        exWorkout.setExserciseType("running");
        exWorkout.setDuration(30);
        exWorkout.setIntencity("mid");
        exWorkout.setCalories(300);
        exWorkout.setWorkoutDate(LocalDate.now());

            WorkoutDTO upworkoutDTO=new WorkoutDTO();
            upworkoutDTO.setExerciseType("running");
            upworkoutDTO.setIntencity("mid");
            upworkoutDTO.setDuration(30);
            upworkoutDTO.setCalories(300);
            upworkoutDTO.setWorkoutDate(LocalDate.now());

            when(workoutRepository.findById(workoutId)).thenReturn(Optional.of(exWorkout));

            when(workoutRepository.save(any(Workout.class))).thenReturn(exWorkout);

        Workout workout = workoutService.updateWorkout(workoutId, upworkoutDTO);

        assertEquals(workout.getExserciseType(), upworkoutDTO.getExerciseType());
        assertEquals(workout.getDuration(), upworkoutDTO.getDuration());
        assertEquals(workout.getIntencity(), upworkoutDTO.getIntencity());
        assertEquals(workout.getWorkoutDate(), upworkoutDTO.getWorkoutDate());

        // Verify that the repository's findById and save methods were called
        verify(workoutRepository, times(1)).findById(workoutId);
        verify(workoutRepository, times(1)).save(exWorkout);
    }
}

