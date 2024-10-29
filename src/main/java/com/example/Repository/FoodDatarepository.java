package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Foodata;

@Repository
public interface FoodDatarepository extends JpaRepository<Foodata, Long> {

}
