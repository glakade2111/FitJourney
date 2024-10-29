package com.example.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.MealDTO;
import com.example.Entity.Food;
import com.example.Entity.Foodata;
import com.example.Entity.Meal;
import com.example.Entity.User;
import com.example.Repository.FoodDatarepository;
import com.example.Repository.FoodRepository;
import com.example.Repository.MealRepository;
import com.example.Repository.UserRepository;

@Service
public class FoodService {

	@Autowired
	private FoodRepository foodrepo;
	
	@Autowired
	private MealRepository mealrepo;
	
	@Autowired
	private FoodDatarepository foodata;
	
	@Autowired
	private UserRepository userepo;
	
	public Food CalculateFood(Food food)
	{
		Food save = foodrepo.save(food);
		return save;
	}
	
	public Foodata calculateMeal(Long Food,double quantity)
	{
	 	Optional<Food> byFood = foodrepo.findById(Food);
	 	Food f1=byFood.get(); 
	 	Foodata data=new Foodata();
	 	data.setCalories(f1.getCalories()*(quantity/100));
	 	data.setCarbs(f1.getCarbs()*(quantity/100));
	 	data.setFats(f1.getFats()*(quantity/100));
	 	data.setFood(f1.getFood());
	 	data.setProtein(f1.getProtein()*(quantity/100));
	 	data.setFoodId(Food);
	 	data.setQuantity(quantity);
	 	foodata.save(data);
	 	return data;
	}
	
	public void calMeal(Foodata meal)
	{
		Foodata calculateMeal = calculateMeal(meal.getFoodId(),meal.getQuantity());
		
	}
	
	public void saveMeal(MealDTO meal)
	{
//		List<Foodata> mealfood = meal.getMealfood();
//		
//		for(Foodata foodata:mealfood)
//		{
//			Optional<Food> byId = foodrepo.findById(foodata.getFoodId());
//			Food food=byId.get();
//			calculateMeal(food.getId(),foodata.getQuantity());
//	
//		}
		Optional<User> byId = userepo.findById(meal.getUid());
		User user = byId.get();
		Meal me=new Meal();
		me.setUser(user);
		
		List<Foodata> foodatas=meal.getMealfood().stream().map(fooddata->calculateMeal(fooddata.getFoodId(), fooddata.getQuantity())).toList();
		
		me.setMealfood(foodatas);
		
		mealrepo.save(me);
	}
}
