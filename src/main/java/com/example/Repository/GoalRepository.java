package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
	
	List<Goal> findByUserId(long userId);

}
