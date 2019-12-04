package com.cognizant.smartshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.smartshop.model.Quantity;



@Repository
public interface QuantityRepository extends JpaRepository<Quantity, String> {
	

}
