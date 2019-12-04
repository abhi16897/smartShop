package com.cognizant.smartShopservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.smartShopservices.services.UserServices;



@RestController
@CrossOrigin
@RequestMapping("")
public class HistoryController {

	@Autowired
	UserServices userService;
	
	@GetMapping("/history/{userId}")
	public void updatePurchaseHistory() {
		
	}
}
