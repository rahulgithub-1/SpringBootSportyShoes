package com.rahul.SpringBootSportyShoe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.SpringBootSportyShoe.Model.Admin;
import com.rahul.SpringBootSportyShoe.Model.Category;
import com.rahul.SpringBootSportyShoe.Model.Product;
import com.rahul.SpringBootSportyShoe.Model.User;
import com.rahul.SpringBootSportyShoe.repository.AdminRepository;
import com.rahul.SpringBootSportyShoe.repository.UserRepository;
import com.rahul.SpringBootSportyShoe.service.AdminService;
import com.rahul.SpringBootSportyShoe.service.CategoryService;
import com.rahul.SpringBootSportyShoe.service.ProductService;
import com.rahul.SpringBootSportyShoe.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired(required = true)
	AdminService adminService;

	@Autowired(required = true)
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	UserService userService;
	

	@PostMapping("/signin")
	public ResponseEntity<?> checkAdmin(@RequestBody Admin admin) {
		System.out.println("Started");
		boolean isAdmin = adminService.verifyAdmin(admin.getEmail(), admin.getPassword());
		if (isAdmin) {
			return new ResponseEntity<>("Success!! Welcome to App", HttpStatus.OK);
		}
		return new ResponseEntity<>("Email or Password Incorrect", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		boolean isProductAdded = productService.addProduct(product);
		if (isProductAdded) {
			return new ResponseEntity<>("Product Added Successfully!!", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Something went Wrong", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/allProduct")
	public ResponseEntity<?> getAllProduct() {
		List<Product> allProduct = productService.getAllProduct();
		return new ResponseEntity<>(allProduct, HttpStatus.OK);
	}

	@GetMapping("/getProduct/{id}")
	public ResponseEntity<?> getProduct(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(productService.getProductById(id), HttpStatus.FOUND);
	}

	@GetMapping("/getAllCategory")
	public ResponseEntity<?> getAllCategory() {
		List<Category> allCategory = categoryService.getAllCategory();
		return new ResponseEntity<>(allCategory, HttpStatus.OK);
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() {
		List<User> allUsers = userService.getAllUser();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);

	}

	@GetMapping("/getUser/{name}")
	public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {
		List<User> searcherUserByName = userService.searcherUserByName(name);
		if (searcherUserByName.size() > 0) {
			return new ResponseEntity<>(searcherUserByName, HttpStatus.FOUND);
		}
		return new ResponseEntity<>("Nothing Found",HttpStatus.NO_CONTENT);
	}

	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
		productService.delteById(id);
		return new ResponseEntity<>("Deleted Record Successfully!!", HttpStatus.OK);
	}

	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
		if (productService.updateProduct(id, product)) {
			return new ResponseEntity<>("Updated Succesfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Something went wrong!!", HttpStatus.BAD_REQUEST);
	}

}
