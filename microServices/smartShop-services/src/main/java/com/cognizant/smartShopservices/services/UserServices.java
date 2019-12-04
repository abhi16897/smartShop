package com.cognizant.smartShopservices.services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.smartShopservices.model.User;
import com.cognizant.smartShopservices.repository.UserRepository;



@Service
public class UserServices {
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public void signup(User user){
		userRepository.save(user);
	}
	
	@Transactional
	public User getUser(String userId) {
		return userRepository.findByUserid(userId);
	}
	@Transactional
	public ArrayList<String> getAllUsers(){
		return userRepository.getAllUsers();
	}
	
}
