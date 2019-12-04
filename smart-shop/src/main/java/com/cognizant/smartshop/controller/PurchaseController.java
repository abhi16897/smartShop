package com.cognizant.smartshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.smartshop.model.Purchase;
import com.cognizant.smartshop.model.PurchaseHistory;
import com.cognizant.smartshop.services.ProductService;
import com.cognizant.smartshop.services.PurchaseService;



@RestController
@CrossOrigin
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	ProductService productService;
	
	@GetMapping("")
    public int getPurchase() {
           int value= purchaseService.getPurchase();
           if(value==0){
        	   return 0;
           }else{
        	   return  purchaseService.getPurchase();
           }
    }

	@PostMapping()
	public void purchase(@RequestBody Purchase purchase) {
		purchaseService.purchase(purchase);
	}

	@PostMapping("/{purchaseId}/{productCode}/{quantity}")
    public boolean purchaseHistory(@PathVariable("purchaseId") int purchaseId,
                  @PathVariable("productCode") String productCode, @PathVariable("quantity") int quantity) {
           return purchaseService.purchaseHistory(purchaseId, productCode, quantity);
    }

	
	@PutMapping()
	public void updatePurchase(@RequestBody Purchase newPurchase) {
		purchaseService.updatePurchase(newPurchase);
	}
	
	@GetMapping("/history/{userId}")
	public List<PurchaseHistory> getPurchaseHistory(@PathVariable("userId") String userId) {
		return purchaseService.getPurchaseHistory(userId);
	}

	// @PutMapping()
	// public void updatePurchase(@RequestBody Purchase newPurchase) {
	// purchaseService.updatePurchase(newPurchase);
	// }

	/*
	 * @PostMapping("/history") public void purchaseHistory(@RequestBody
	 * PurchaseHistory purchaseHistory) {
	 * purchaseService.purchaseHistory(purchaseHistory); }
	 */

	

	// @GetMapping("/history/{userId}")
	// public PurchaseHistory updatePurchaseHistory(@PathVariable("userId")
	// String userId) {
	// return purchaseService.updatePurchaseHistory(userId);
	// }
}
