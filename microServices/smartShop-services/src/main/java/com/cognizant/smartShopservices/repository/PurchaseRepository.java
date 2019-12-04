package com.cognizant.smartShopservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.smartShopservices.model.Purchase;
import com.cognizant.smartShopservices.model.User;

;



@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String>{

	Purchase findById(int purchaseId);
	
	List<Purchase> findByUser(User user);
	@Query(nativeQuery=true, value="SELECT pu_id FROM purchase ORDER BY pu_id DESC LIMIT 1")
    int findLastId();

}
