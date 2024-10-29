package com.example.DTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GoalDTO {

	
	 	private String description; // Goal description, e.g., "burn 5000 calories this month"
	    private int targetCalories; // The target number of calories the user wants to burn
	    private int progressCalories; // The current progress towards the goal
	    private LocalDate startDate; // The start date of the goal
	    private LocalDate endDate;   // The end date of the goal
	    private long userid;

}
