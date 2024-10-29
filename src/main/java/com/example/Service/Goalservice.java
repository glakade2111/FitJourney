package com.example.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.DTO.GoalDTO;
import com.example.Entity.Goal;
import com.example.Entity.User;
import com.example.Repository.GoalRepository;
import com.example.Repository.UserRepository;

@Service
public class Goalservice {

	@Autowired
	GoalRepository repo;
	
	@Autowired
	UserRepository repou;
	
	@Autowired
	NotificationService notification;
	
	public Goal createGoal(GoalDTO goal)
	{
			
		Optional<User> byUserId = repou.findById(goal.getUserid());
		User user=byUserId.get();
		Goal g=new Goal();
		g.setDescription(goal.getDescription());
		g.setTargetCalories(goal.getTargetCalories());
		g.setStartDate(goal.getStartDate());
		g.setEndDate(goal.getEndDate());
		g.setProgressCalories(goal.getProgressCalories());
		g.setUser(user);
		return repo.save(g);
	}
	
	public List<Goal> getGoalByUserId(long userid)
	{
		return repo.findByUserId(userid);
	}
	
	public Goal updateGoalProgress(long goalId,int additionalcalories)
	{
		Optional<Goal> byId = repo.findById(goalId);
		Goal goal=byId.get();
		
		goal.setProgressCalories(goal.getProgressCalories()+additionalcalories);
		
		return goal;
	}

	public void updateGoal(Goal goal) {
 
			Optional<Goal> byId = repo.findById(goal.getId());
			Goal existing=byId.get();
		 	
			existing.setProgressCalories(goal.getProgressCalories());
			repo.save(existing);
		
	}
	
//	@Scheduled(fixedRate = 1*60*1000)
//	@Scheduled(cron = "0 0 12 * * *", zone = "Asia/Kolkata")
	public void notification()
	{
		System.out.println("started");
			List<User> all = repou.findAll();
			
			for(User user:all)
			{
				for(Goal goal:user.getGoal())
				{
					if(goal.getEndDate().isBefore(LocalDate.now()))
					{
						notification.sendGoalNotification(user.getEmail(),"Goal is expire ","please check whether you reach goal");
					}
				}
			}
			System.out.println("ended");
	}
}
