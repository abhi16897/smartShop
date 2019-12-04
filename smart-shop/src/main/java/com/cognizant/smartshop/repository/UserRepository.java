package com.cognizant.smartshop.repository;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.smartshop.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, String> {

		User findByUserid(String id);
		ArrayList<User> findByStatus(String status);

}
