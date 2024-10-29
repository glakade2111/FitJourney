package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.WorkoutDTO;
import com.example.Entity.Workout;
import com.example.Service.WorkoutIn;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

	@Autowired
	WorkoutIn workoutIn;
	
	@PostMapping("/log")
	public ResponseEntity<Workout> saveWorkoutlog(@RequestBody WorkoutDTO workout)
	{
		Workout saveworkoutLog = workoutIn.saveworkoutLog(workout);
		return ResponseEntity.ok(saveworkoutLog);
	}
	
	@GetMapping("/log/{id}")
	public ResponseEntity<List<Workout>> getWorkoutlog(@PathVariable long id)
	{
	 List<Workout> getworkoutbyuser = workoutIn.getworkoutbyuser(id);
		return ResponseEntity.ok(getworkoutbyuser);
	}
}
