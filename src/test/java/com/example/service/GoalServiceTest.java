package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.Entity.Workout;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.DTO.GoalDTO;
import com.example.Entity.Goal;
import com.example.Entity.User;
import com.example.Repository.GoalRepository;
import com.example.Repository.UserRepository;
import com.example.Service.Goalservice;

@SpringBootTest
public class GoalServiceTest {

	@InjectMocks
	Goalservice goalservice;
	
	@Mock
	GoalRepository goalRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Test
	void saveGoal()
	{
		User user=new User();
		user.setId(1);
		
		GoalDTO goalDTO=new GoalDTO();
		goalDTO.setDescription("burn calories ");
		goalDTO.setTargetCalories(10000);
		goalDTO.setProgressCalories(0);
		goalDTO.setStartDate(LocalDate.now().minusDays(1));
		goalDTO.setEndDate(LocalDate.now().plusDays(1));
		goalDTO.setUserid(1);
		
		
		Goal goal=new Goal();
		goal.setDescription(goalDTO.getDescription());
		goal.setTargetCalories(goalDTO.getTargetCalories());
		goal.setProgressCalories(goalDTO.getProgressCalories());
		goal.setStartDate(goalDTO.getStartDate());
		goal.setEndDate(goalDTO.getEndDate());
		goal.setUser(user);
		
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(goalRepository.save(any(Goal.class))).thenReturn(goal);

		Goal goal2 = goalservice.createGoal(goalDTO);
		                    
		assertNotNull(goal2);
		assertEquals(goal.getDescription(), goal2.getDescription());
		assertEquals(goal.getStartDate(),goal2.getStartDate());
		assertEquals(goal.getEndDate(),goal2.getEndDate());
		assertEquals(goal.getUser(),goal2.getUser());
		verify(goalRepository,times(1)).save(any(Goal.class));

	}


}
