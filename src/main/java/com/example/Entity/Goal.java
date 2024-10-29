package com.example.Entity;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Goal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    private String description; // Goal description, e.g., "burn 5000 calories this month"
    private int targetCalories; // The target number of calories the user wants to burn
    private int progressCalories; // The current progress towards the goal
    private LocalDate startDate; // The start date of the goal
    private LocalDate endDate;   // The end date of the goal

	@JsonBackReference
    @ManyToOne
    User user;
}
