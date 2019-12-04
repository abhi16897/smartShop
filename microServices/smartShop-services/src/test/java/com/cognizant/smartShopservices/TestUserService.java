package com.cognizant.smartShopservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cognizant.smartShopservices.model.User;
import com.cognizant.smartShopservices.repository.UserRepository;
import com.cognizant.smartShopservices.services.PendingServices;
import com.cognizant.smartShopservices.services.UserServices;



public class TestUserService {

	@InjectMocks
	UserServices userService;

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	PendingServices pendingService;
	
	

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findUserByIdTest() {
		User user = new User();
		user.setLastname("Sankalp");
		user.setLastname("Khawade");
		user.setAge(21);
		user.setGender("Male");
		user.setContact("9750277077");
		user.setUserid("Lonavala");
		user.setPassword("pwd");
		user.setUserType("U");
		user.setSecretQuestion("Dream place");
		user.setAnswer("Pune");
		user.setStatus("A");

		when(userRepository.findByUserid("Lonavala")).thenReturn(user);

		// test
		User testUser = userService.getUser("Lonavala");

		assertEquals("Lonavala", testUser.getUserid());
		assertEquals("Sankalp", testUser.getFirstname());
		assertEquals("Khawade", testUser.getLastname());
		assertEquals(21, testUser.getAge());
		assertEquals("Male", testUser.getGender());
		assertEquals("9750277077", testUser.getContact());
		assertEquals("pwd", testUser.getPassword());
		assertEquals("U", testUser.getUserType());
		assertEquals("Dream place", testUser.getSecretQuestion());
		assertEquals("Pune", testUser.getAnswer());
		assertEquals("A", testUser.getStatus());

	}

	@Test
	public void findUserByStatusTest() {
		User user = new User();
		user.setLastname("Sankalp");
		user.setLastname("Khawade");
		user.setAge(21);
		user.setGender("Male");
		user.setContact("9750277077");
		user.setUserid("Lonavala");
		user.setPassword("pwd");
		user.setUserType("U");
		user.setSecretQuestion("Dream place");
		user.setAnswer("Pune");
		user.setStatus("P");

		List<User> users = new ArrayList<>();
		users.add(user);

		when(pendingService.getPendingUser()).thenReturn((ArrayList<User>) users);

		// test
		List<User> testUserList =pendingService.getPendingUser();

		for (User testUser : testUserList) {
			assertEquals("Lonavala", testUser.getUserid());
			assertEquals("Sankalp", testUser.getFirstname());
			assertEquals("Khawade", testUser.getFirstname());
			assertEquals(21, testUser.getAge());
			assertEquals("Male", testUser.getGender());
			assertEquals("9750277077", testUser.getContact());
			assertEquals("pwd", testUser.getPassword());
			assertEquals("U", testUser.getUserType());
			assertEquals("Dream place", testUser.getSecretQuestion());
			assertEquals("Pune", testUser.getAnswer());
			assertEquals("P", testUser.getStatus());
		}
	}
	
}
