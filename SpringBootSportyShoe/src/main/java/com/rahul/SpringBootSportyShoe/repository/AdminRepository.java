package com.rahul.SpringBootSportyShoe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rahul.SpringBootSportyShoe.Model.Admin;
import com.rahul.SpringBootSportyShoe.Model.PurchaseReport;
import com.rahul.SpringBootSportyShoe.Model.User;

@Component
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	@Query(value = "SELECT * FROM ADMIN WHERE EMAIL = ?1", nativeQuery = true)
	Admin findByAdminEmail(String email);
	
	@Query(value = "SELECT product.name,count(*) As stock_clear FROM Orders inner join product on product.id = orders.productId inner join users on users.id = orders.userId group by product.id;", nativeQuery = true)
	List<PurchaseReport> findpurchaseReport();
}
