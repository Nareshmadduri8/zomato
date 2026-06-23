package com.example.zomato.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.example.zomato.Entity.Customer;
import com.example.zomato.Entity.Restaurant;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer>{

//	 List<Restaurant> findByNameAndAddressCity(String name, String city);
}
