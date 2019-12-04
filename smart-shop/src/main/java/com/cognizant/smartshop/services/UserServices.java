package com.cognizant.smartshop.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.smartshop.model.User;
import com.cognizant.smartshop.repository.UserRepository;

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
}
