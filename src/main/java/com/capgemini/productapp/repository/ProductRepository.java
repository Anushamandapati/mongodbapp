package com.capgemini.productapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.capgemini.productapp.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Integer>
{
	@Query("{'productCategory' :?0}")
	public List<Product> findByProductCategory(String category);
	
	@Query("{'productPrice' : { '$lt' : 18000}}")
	public List<Product> findByProductPrice();
	
	@Query("{'productPrice' : { '$lt' : 18000}, '$and' :[{'productCategory' : ?0 }]}")
	public List<Product> findByCategoryAndPrice(String productCategory);
}
