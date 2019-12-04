package com.cognizant.smartShopservices.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.smartShopservices.SmartShopServicesApplication;
import com.cognizant.smartShopservices.model.User;
import com.cognizant.smartShopservices.services.PendingServices;

;

@RestController
@RequestMapping("/pendings")
@CrossOrigin("http://localhost:4200")
public class PendingRequests {
	 private static final Logger LOGGER = LoggerFactory.getLogger(SmartShopServicesApplication.class);
	@Autowired
	PendingServices pendingService;
	
	@GetMapping
	public ArrayList<User> getPendingUsers(){
		return pendingService.getPendingUser();
	}
	@PutMapping(value = "/{userid}/{status}")
    public void approvedAdmin(@PathVariable("userid") String userId, @PathVariable("status") String status) {
           User pendingAdmin = pendingService.findByUserId(userId);
           LOGGER.info("Old User Status: " + pendingAdmin.getStatus());
           LOGGER.info("New User Status: " + status);
           pendingAdmin.setStatus(status);
           if (status.equalsIgnoreCase("A"))
        	   pendingService.saveUser(pendingAdmin);
           else if (status.equalsIgnoreCase("D"))
        	   pendingService.deleteById(userId);
    }

}
