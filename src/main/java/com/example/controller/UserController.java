package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.MealDTO;
import com.example.Entity.Food;
import com.example.Entity.Foodata;
import com.example.Entity.Meal;
import com.example.Entity.User;
import com.example.Service.FoodService;
import com.example.Service.UserServiceIn;

@RestController
public class UserController {

	@Autowired
	UserServiceIn userservice;
	
	@Autowired
	FoodService service;
	
	@PostMapping("/create")
	public ResponseEntity<Void>  CreateUser(@RequestBody User user)
	{
		userservice.PostUser(user);
		return ResponseEntity.status(201).build();
	}
	
	@GetMapping("/showall")
	public ResponseEntity<List<User>>  GetAllUser()
	{
		 List<User> allUsers = userservice.AllUsers();
		 return ResponseEntity.ok(allUsers);
	}
	
	@GetMapping("/showbyid/{id}")
	public ResponseEntity<User>  GetUser(@PathVariable long id)
	{
		User getUser = userservice.GetUser(id);
		return ResponseEntity.ok(getUser);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User>  PutAllUser(@PathVariable long id,@RequestBody User user )
	{
		 User putUser = userservice.PutUser(id,user);
		 return ResponseEntity.ok(putUser);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void>  Delete(@PathVariable long id )
	{
		userservice.DeleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/inactive")
	public ResponseEntity< List<User>> InactiveUser()
	{
		List<User> inactiveUser = userservice.InactiveUser();
		return ResponseEntity.ok(inactiveUser);
	}
	
	@PostMapping("/food")
	public ResponseEntity<Food> saveFood(@RequestBody Food food)
	{
		Food calculateFood = service.CalculateFood(food);
		return ResponseEntity.ok(calculateFood);
	}
	
	@PostMapping("/food/cal/{food}/{quantity}")
	public ResponseEntity<Foodata> saveFood(@PathVariable long food ,@PathVariable double quantity)
	{
		Foodata calculateFood = service.calculateMeal(food, quantity);
		return ResponseEntity.ok(calculateFood);
	}
	
	@PostMapping("/food/meal")
	public ResponseEntity<Void> saveMeal(@RequestBody MealDTO meal)
	{
		service.saveMeal(meal);
		return ResponseEntity.noContent().build();
	}
}
