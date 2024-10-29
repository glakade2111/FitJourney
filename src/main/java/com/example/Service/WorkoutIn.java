package com.example.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DTO.WorkoutDTO;
import com.example.Entity.Workout;

@Service
public interface WorkoutIn {

	public Workout saveworkoutLog(WorkoutDTO workout);
	
	public List<Workout> getworkoutbyuser(long userid);
}
