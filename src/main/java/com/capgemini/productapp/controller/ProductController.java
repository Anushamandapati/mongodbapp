package com.capgemini.productapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;
import com.capgemini.productapp.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		ResponseEntity<Product> responseEntity = 
				new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.OK);
		
		return responseEntity;
	}
	
	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(Product product) {
		try {
			Product productFromDb = 
					productService.findProductById(product.getProductId());
			if(productFromDb != null)
				return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
		}
		catch(ProductNotFoundException exception) {
			//logged the exception
		}		
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable int productId) {
		try {
			Product productFromDb = 
					productService.findProductById(productId);
			return new ResponseEntity<Product>(productFromDb, HttpStatus.OK);
		}
		catch(ProductNotFoundException exception) {
			//logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Product> deleteProduct(Product product) {
		try {
			Product productFromDb = 
					productService.findProductById(product.getProductId());
			if(productFromDb != null) {
				productService.deleteProduct(productFromDb);
				return new ResponseEntity<Product>(HttpStatus.OK);
			}
		}
		catch(ProductNotFoundException exception) {
			//logged the exception
		}		
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/productCatk/{productCategory}")
	public ResponseEntity<List<Product>> findProductByCategory(@PathVariable String productCategory) {
		return new ResponseEntity<List<Product>> (productService.findProductByCategory(productCategory),HttpStatus.OK);

	}
	
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>> findProductByPrice(){
		return new ResponseEntity<List<Product>> (productService.findProductByPrice(),HttpStatus.OK);
	}
	
	@GetMapping("/productCat/{productCategory}")
	public ResponseEntity<List<Product>> findProductByCategoryAndPrice(@PathVariable String productCategory){
		return new ResponseEntity<List<Product>> (productService.findProductByCategoryAndPrice(productCategory),HttpStatus.OK);
	}
	
	
	/*@GetMapping("/products/categoryandprice")
	public ResponseEntity<List<Product>> findProductById(@RequestParam String category, @RequestParam double from,
			@RequestParam double to) {
		List<Product> productFromDb = productService.getProductByCategoryAndPrice(category, from, to);
		System.out.println(productFromDb);
		return new ResponseEntity<List<Product>>(productFromDb, HttpStatus.OK);

	}
	

	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> findProductByName(@RequestParam String productName) {
		System.out.println(productName);
		List<Product> productFromDb = productService.getProductByName(productName);
		System.out.println(productFromDb);
		return new ResponseEntity<List<Product>>(productFromDb, HttpStatus.OK);

	}*/

	
/*	
	@GetMapping("/products/{productCategory}")
	public ResponseEntity<Product> findByProductCategory(@PathVariable String productCategory)
	{
		 ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productService.findByProductCategory(productCategory);
		 return responseEntity;
	}
	*/
}
