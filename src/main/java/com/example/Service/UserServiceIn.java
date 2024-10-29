package com.example.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Entity.User;
@Service
public interface UserServiceIn {
	
	public List<User> AllUsers();
	
	public User GetUser(long id);
	
	public void PostUser( User user);
	
	public User PutUser(long id,User user);
	
	public void DeleteUser(long id );
	
	public List<User> InactiveUser();

}
