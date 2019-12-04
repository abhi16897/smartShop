package com.cognizant.smartshop.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.smartshop.model.Product;
import com.cognizant.smartshop.repository.ProductRepository;



@Component
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Transactional
	public void addProduct(Product product) {
		Product exists = productRepository.findByProductCode(product.getProductCode());
		if (exists == null)
			productRepository.save(product);
		else {
			exists.setStock(exists.getStock() + product.getStock());
		}

	}

	@Transactional
	public List<Product> getProduct() {
		return productRepository.findAll();
	}
	
	@Transactional
	public Product findProductByCode(String productCode) {
		return productRepository.findByProductCode(productCode);
	}

	@Transactional
	public void modifyProduct(Product product) {
		productRepository.save(product);
	}

	@Transactional
	public void deleteProduct(String productCode) {
		Product product = productRepository.findByProductCode(productCode);
		productRepository.delete(product);
	}

	@Transactional
	public List<String> findProductType() {
		return productRepository.findProductType();
	}

	@Transactional
	public List<Product> findProductByType(String productType) {
		return productRepository.findByProductType(productType);
	}

	@Transactional
	public void updateStock() {

	}

}
