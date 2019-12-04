package com.cognizant.smartShopservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.smartShopservices.model.Quantity;





@Repository
public interface QuantityRepository extends JpaRepository<Quantity, String> {
	

}
