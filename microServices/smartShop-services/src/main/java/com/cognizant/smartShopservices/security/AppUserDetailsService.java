package com.cognizant.smartShopservices.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.smartShopservices.SmartShopServicesApplication;
import com.cognizant.smartShopservices.model.User;
import com.cognizant.smartShopservices.repository.UserRepository;




@Service
public class AppUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmartShopServicesApplication.class);

	@Autowired
	UserRepository userRepository;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		LOGGER.info("Start");
		LOGGER.debug("UserRepository:{}", userRepository);
		User user = userRepository.findByUserid(userId);
		LOGGER.debug("User:{}", user);
		
		if (user != null) {
			LOGGER.info("End");
			return new AppUser(user);
		} else {
			throw new UsernameNotFoundException(userId);
		}
	}
}
