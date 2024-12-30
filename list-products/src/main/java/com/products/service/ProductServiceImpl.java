package com.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.model.Product;
import com.products.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService{

	//--------------------------------------------------------------
	@Autowired
	private ProductRepository 	productRepository;
	//--------------------------------------------------------------
	

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public void saveProduct(Product entity) {
		productRepository.save(entity);
	}

	public Optional<Product> getProductById(Long id) {		
		return productRepository.findById(id);
	}

	public void deleteProduct(Product entity) {		
		productRepository.delete(entity);
	}

}
