package com.products.service;

import java.util.List;
import java.util.Optional;
import com.products.model.Product;




public interface ProductService {
	
	public List<Product> getAllProducts();

	public Optional<Product> getProductById(Long id);

	public void saveProduct(Product entity);

	public void deleteProduct(Product entity);

}
