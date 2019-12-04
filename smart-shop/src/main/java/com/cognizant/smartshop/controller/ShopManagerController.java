package com.cognizant.smartshop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.smartshop.SmartShopApplication;
import com.cognizant.smartshop.model.Product;
import com.cognizant.smartshop.services.ProductService;



@RestController
@CrossOrigin
@RequestMapping("/product")
public class ShopManagerController {

	@Autowired
	ProductService productService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SmartShopApplication.class);

	@PostMapping("")
	public void addProduct(@RequestBody Product product) {
		LOGGER.info("START OF ADD PRODUCT - SHOP MANAGER");
		productService.addProduct(product);
	}
	
	@GetMapping("")
	public List<Product> findProduct() {
		return productService.getProduct();
	}

	@GetMapping("/{code}")
	public Product findProductByCode(@PathVariable("code") String productCode) {
		return productService.findProductByCode(productCode);
	}

	@PutMapping("/update")
	public void modifyProduct(@RequestBody Product product) {
		productService.modifyProduct(product);
	}

	@DeleteMapping("/{code}")
	public void deleteProduct(@PathVariable("code") String productCode) {
		productService.deleteProduct(productCode);
	}
	
	@GetMapping("/type")
	public List<String> findProductType() {
		return productService.findProductType();
	}
	
	@GetMapping("/type/{type}")
	public List<Product> findProductByType(@PathVariable("type") String productType) {
		return productService.findProductByType(productType);
	}
	

}
