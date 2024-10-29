package com.example.DTO;

import java.util.List;

import com.example.Entity.Foodata;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MealDTO {

	
	private long uid;
	private List<Foodata> mealfood;
	private long mid;
}
