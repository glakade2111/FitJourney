package com.example.Entity;

import java.util.List;

import com.example.DTO.MealDTO;
import com.example.Service.FoodService;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Meal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
//    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, fetch =FetchType.EAGER)
//	private List<Foodata> food;
    
    @ManyToOne
    private User user;
    
    private double quantity;
      
    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Foodata> mealfood;    
    
//    @PrePersist
//    public void prepersist()
//    {
//    	for(Foodata food:mealfood)
//    	{
//    		 FoodService f1=new FoodService();
//    		 f1.calMeal(food);
//    	}
//    }
}
