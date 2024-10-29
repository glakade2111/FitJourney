package com.example.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDTO {
	
	private String exerciseType;
	private int duration;
	private int calories;
	private String intencity;
	private LocalDate workoutDate;
	private long userid;

	

}
