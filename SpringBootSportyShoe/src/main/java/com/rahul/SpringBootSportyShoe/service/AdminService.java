package com.rahul.SpringBootSportyShoe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.SpringBootSportyShoe.Model.Admin;
import com.rahul.SpringBootSportyShoe.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired(required = true)
	private AdminRepository adminRepository;
	
	public boolean verifyAdmin(String username,String password) {
		Admin admin = adminRepository.findByAdminEmail(username);
		if(admin.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	//TODO: need to complete
	public boolean addProduct() {
		return true;
	}
}
