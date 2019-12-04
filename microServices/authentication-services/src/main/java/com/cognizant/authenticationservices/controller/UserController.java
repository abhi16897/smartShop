package com.cognizant.authenticationservices.controller;



import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authenticationservices.AuthenticationServicesApplication;
import com.cognizant.authenticationservices.model.User;
import com.cognizant.authenticationservices.services.UserServices;

;



@RestController
@RequestMapping("/users")
public class UserController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServicesApplication.class);
	@Autowired
	UserServices userServices;
	@PostMapping
	public void signup(@RequestBody @Valid User user){
	User userOne=new User();
	userOne.setUserid(user.getUserid());
	userOne.setGender(user.getGender());
	userOne.setFirstname(user.getFirstname());
	userOne.setLastname(user.getLastname());
	userOne.setAge(user.getAge());
	userOne.setContact(user.getContact());
	userOne.setPassword(passwordEncoder()
            .encode(user.getPassword()));
	userOne.setUserType(user.getUserType());
	if(user.getUserType().equals("U")){
		userOne.setStatus("A");
	}
	if(user.getUserType().equals("A")){
		userOne.setStatus("P");
	}
	userOne.setAnswer(user.getAnswer());
	userOne.setSecretQuestion(user.getSecretQuestion());
	userServices.signup(userOne);
	}
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable("userId") String userId) {
		return userServices.getUser(userId);
	}
	@GetMapping()
	public ArrayList<String> getAllUsers(){
		return userServices.getAllUsers();
	}
	
}
