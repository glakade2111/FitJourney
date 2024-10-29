package com.example.Entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	private LocalDate lastWorkout;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch =FetchType.EAGER)
	private List<Workout> workout;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch =FetchType.EAGER)
	private List<Goal> goal;
	
	@OneToMany
	private List<Meal> meal;
}
