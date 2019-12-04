package com.cognizant.smartShopservices.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.smartShopservices.SmartShopServicesApplication;
import com.cognizant.smartShopservices.model.Product;
import com.cognizant.smartShopservices.model.Purchase;
import com.cognizant.smartShopservices.model.PurchaseHistory;
import com.cognizant.smartShopservices.model.Quantity;
import com.cognizant.smartShopservices.model.User;
import com.cognizant.smartShopservices.repository.ProductRepository;
import com.cognizant.smartShopservices.repository.PurchaseHistoryRepository;
import com.cognizant.smartShopservices.repository.PurchaseRepository;
import com.cognizant.smartShopservices.repository.QuantityRepository;
import com.cognizant.smartShopservices.repository.UserRepository;




@Component
public class PurchaseService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PurchaseRepository purchaseRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	PurchaseHistoryRepository purchaseHistoryRepository;
	
	@Autowired
	QuantityRepository quantityRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmartShopServicesApplication.class);

	@Transactional
	public void purchase(Purchase purchase) {
		LOGGER.info("Purchase: "+purchase);
		purchaseRepository.save(purchase);
	}
	@Transactional
    public int getPurchase() {
           return purchaseRepository.findLastId();
    }

    @Transactional
    public boolean purchaseHistory(int purchaseId, String productCode, int quantityId) {
           LOGGER.info("START");
           Product product = productRepository.findByProductCode(productCode);
           LOGGER.info("Product: {}", product);
           if (product.getStock() < quantityId) {
                  return false;
           }
           Quantity quantity = new Quantity();
           quantity.setQuantity(quantityId);
           quantityRepository.save(quantity);
           product.getQuantity().add(quantity);
           purchaseHistoryRepository.save(productCode, purchaseId, quantity.getId());
           return true;
    }


	
	@Transactional
	public void updatePurchase(Purchase newPurchase) {
		LOGGER.info("START");
		Purchase oldPurchase = purchaseRepository.findById(newPurchase.getId());
		LOGGER.info("Purchase: {}",oldPurchase);
		if (oldPurchase != null) {
			oldPurchase.setTotalAmount(newPurchase.getTotalAmount());
			LOGGER.info("Purchase: {}",oldPurchase);
			List<Product> productList = oldPurchase.getProducts();
			for(Product product : productList) {
				List<Quantity> quantityList = product.getQuantity();
				for(Quantity quantity : quantityList)
					product.setStock(product.getStock()-quantity.getQuantity());
				LOGGER.info("Product: {}",product);
			}
			purchaseRepository.save(oldPurchase);
		}
	}
	
	@Transactional
    public List<PurchaseHistory> getPurchaseHistory(String userId) {
           User user = userRepository.findByUserid(userId);
           List<Purchase> purchases = purchaseRepository.findByUser(user);
           LOGGER.info("Purchases: {}", purchases);
           List<PurchaseHistory> purchaseHistories = new ArrayList<>();
           for (Purchase purchase : purchases) {
                  LOGGER.info("Purchase: {}", purchase);
                  List<PurchaseHistory> purchaseHistory = purchaseHistoryRepository.findByPurchase(purchase);
                  for (PurchaseHistory pHistory : purchaseHistory) {
                        purchaseHistories.add(pHistory);
                  }
           }
           LOGGER.info("Purchase History: {}", purchaseHistories);
           return purchaseHistories;
    }
}
