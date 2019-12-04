package com.cognizant.smartShopservices.services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.smartShopservices.model.User;
import com.cognizant.smartShopservices.repository.UserRepository;



@Service
public class PendingServices {
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public ArrayList<User> getPendingUser(){
		return userRepository.findByStatus("P");
	}
    @Transactional
    public void saveUser(User user) {
           userRepository.save(user);
    }
    
    @Transactional
    public void deleteById(String userId) {
           userRepository.deleteById(userId);
    }
	public User findByUserId(String userId) {
		// TODO Auto-generated method stub
			return userRepository.findByUserid(userId);
	}

}
