package com.rahul.SpringBootSportyShoe.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.SpringBootSportyShoe.Model.Product;
import com.rahul.SpringBootSportyShoe.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public boolean addProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		if(savedProduct.getName().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public Product getProductById(Integer id){
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			return product.get();
		}
		return null;
	}
	
	public void delteById(Integer id) {
		productRepository.deleteById(id);
	}
	
	public boolean updateProduct(Integer id,Product product) {
		Optional<Product> getProduct = productRepository.findById(id);
		if(getProduct.isPresent()) {
			Product preProduct = getProduct.get();
			preProduct.setCategoryId(product.getCategoryId());
			preProduct.setCompany(product.getCompany());
			preProduct.setName(product.getName());
			preProduct.setPrice(product.getPrice());
			productRepository.save(preProduct);
			return true;
		}
		return false;
	}
}
