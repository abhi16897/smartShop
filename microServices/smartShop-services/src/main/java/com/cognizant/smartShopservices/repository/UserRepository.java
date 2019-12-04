package com.cognizant.smartShopservices.repository;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.smartShopservices.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

		User findByUserid(String id);
		ArrayList<User> findByStatus(String status);
		@Query(nativeQuery=true, value="SELECT user_id FROM user")
		ArrayList<String> getAllUsers();

}
