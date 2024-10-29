package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.GoalDTO;
import com.example.Entity.Goal;
import com.example.Service.Goalservice;

@RestController
@RequestMapping("/goal")
public class GoalController {

	@Autowired
	Goalservice service;
	
	@PostMapping
	public Goal createGoal(@RequestBody GoalDTO goal)
	{
		return service.createGoal(goal);
	}
	
	@GetMapping("/finduser/{userid}")
	public List<Goal> findGoalByUserId(@PathVariable long userid)
	{
		return service.getGoalByUserId(userid);
	}
	
	@GetMapping("/updateprogress/{goalid}/{addcal}")
	public Goal updateGoalProgress(@PathVariable long goalid,@PathVariable int addcal)
	{
		return service.updateGoalProgress(goalid, addcal);
	}
}
