package com.capgemini.productapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;
import com.capgemini.productapp.repository.ProductRepository;
import com.capgemini.productapp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(int productId) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent())
			return optionalProduct.get();
		throw new ProductNotFoundException("Product does not exists");
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}
	@Override
	public List<Product> findProductByCategory(String productCategory) {
		return productRepository.findByProductCategory(productCategory);
	}

	/*@Override
	public List<Product> getProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}*/

/*	@Override
	public List<Product> getProductByName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	@Override
	public List<Product> findProductByPrice() {
		return productRepository.findByProductPrice();
	}

	@Override
	public List<Product> findProductByCategoryAndPrice(String productCategory) {
		return productRepository.findByCategoryAndPrice(productCategory);
	}

	

}
