package com.example.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.DTO.WorkoutDTO;
import com.example.Entity.Goal; 
import com.example.Entity.User;
import com.example.Entity.Workout;
import com.example.Repository.UserRepository;
import com.example.Repository.WorkoutRepository;

@Component
public class WorkoutService implements WorkoutIn {
	
	@Autowired
	WorkoutRepository workoutrepo;
	
	@Autowired
	UserRepository users;
	
	@Autowired
	Goalservice goals;

	@Override
	public Workout saveworkoutLog(WorkoutDTO workout) {
		
		
		int calculateCalories = CalculateCalories(workout);
//		workout.setCalories(calculateCalories);
//		
		
			Optional<User> get = users.findById(workout.getUserid());
			User user=get.get();
			Workout workout1=new Workout();
			workout1.setExserciseType(workout.getExerciseType());
			workout1.setDuration(workout.getDuration());
			workout1.setCalories(calculateCalories);
			workout1.setIntencity(workout.getIntencity());
			workout1.setWorkoutDate(workout.getWorkoutDate());
			workout1.setUser(user);
			
			updateGoalprogress(workout1);
			return workoutrepo.save(workout1);
	}

	public void updateGoalprogress(Workout workout1) {

				List<Goal> goalByUserId = goals.getGoalByUserId(workout1.getUser().getId());
				
				for(Goal goal:goalByUserId)
				{
					if(goal.getEndDate().isAfter(LocalDate.now()))
					{
						goal.setProgressCalories(goal.getProgressCalories()+workout1.getCalories());
						goals.updateGoal(goal);
					}
				}
	}

	public int CalculateCalories(WorkoutDTO workout) {
	    int caloriesPerMinute = 0;
	    
	    // Add null check for exerciseType
	    String exerciseType = workout.getExerciseType();
	    if (exerciseType != null) {
	        switch (exerciseType.toLowerCase()) {
	            case "running":
	                caloriesPerMinute = 10;
	                break;
	            case "cycling":
	                caloriesPerMinute = 8;
	                break;
	            case "weightlifting":
	                caloriesPerMinute = 6;
	                break;
	            default:
	                caloriesPerMinute = 5;
	        }
	    } else {
	        // Handle the case when exerciseType is null
	        throw new IllegalArgumentException("Exercise type cannot be null");
	    }
	    
	    return workout.getDuration() * caloriesPerMinute;
	}

	@Override
	public List<Workout> getworkoutbyuser(long userid) {
		// TODO Auto-generated method stub
		return workoutrepo.findByUserId(userid);
	}

	public Workout updateWorkout(long workoutId, WorkoutDTO updatedWorkoutDTO) {
		Optional<Workout> workoutOptional = workoutrepo.findById(workoutId);

		if (workoutOptional.isPresent()) {
			Workout workout = workoutOptional.get();
			workout.setExserciseType(updatedWorkoutDTO.getExerciseType());
			workout.setDuration(updatedWorkoutDTO.getDuration());
			workout.setCalories(CalculateCalories(updatedWorkoutDTO)); // Assume you have a method for calorie calculation
			workout.setIntencity(updatedWorkoutDTO.getIntencity());
			workout.setWorkoutDate(updatedWorkoutDTO.getWorkoutDate());

			return workoutrepo.save(workout);  // Save the updated workout
		} else {
			throw new IllegalArgumentException("Workout not found for ID: " + workoutId);
		}
	}
}
