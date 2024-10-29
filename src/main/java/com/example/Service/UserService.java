package com.example.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Entity.Goal;
import com.example.Entity.Meal;
import com.example.Entity.User;
import com.example.Entity.Workout;
import com.example.Repository.UserRepository;

@Component
public class UserService implements UserServiceIn {

	@Autowired
	UserRepository UserRepo;
	
	@Autowired
	WorkoutService work;
	
	@Override
	public List<User> AllUsers() {
		
		List<User> all = UserRepo.findAll();
		
//		for(User user:all)
//		{
//		List<Workout> getworkoutbyuser = work.getworkoutbyuser(user.getId());
//		user.setWorkout(getworkoutbyuser);
//		}
		return all;
	}

	@Override
	public User GetUser(long id) {
		// TODO Auto-generated method stub
		 Optional<User> byId = UserRepo.findById(id);
		 
		 return byId.get();
	}

	@Override
	public void PostUser(User user) {
		
//		List<Workout> getworkoutbyuser = work.getworkoutbyuser(user.getId());
//		user.setWorkout(getworkoutbyuser);
		
	    if (user.getWorkout() != null) {
	        for (Workout workout : user.getWorkout()) {
	            workout.setUser(user); // Set the user reference in each workout
				work.updateGoalprogress(workout);

	        }
	    }
	    
	    if (user.getGoal() != null) {
	        for (Goal goal : user.getGoal()) {
	            goal.setUser(user); // Set the user reference in each workout
	        }
	    }
	    
	    if (user.getMeal()!= null) {
	        for (Meal goal : user.getMeal()) {
	            goal.setUser(user); // Set the user reference in each workout
	        }
	    }
	   
		UserRepo.save(user);
	}

	@Override
	public User PutUser(long id,User user) {
		
		Optional<User> byId = UserRepo.findById(id);
		User userOld=byId.get();
		userOld.setName(user.getName());
		userOld.setEmail(user.getEmail());
		userOld.setLastWorkout(user.getLastWorkout());

		
		return UserRepo.save(userOld);
	}

	@Override
	public void DeleteUser(long id) {
	 UserRepo.deleteById(id);
	}

	@Override
	public List<User> InactiveUser() {

		LocalDate inactive=LocalDate.now().minusDays(7);
		return UserRepo.findByLastWorkoutBefore(inactive);
	}

}
