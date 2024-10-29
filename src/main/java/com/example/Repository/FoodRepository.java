package com.example.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long>{
	
    Optional<Food> findByFood(String food);
}
