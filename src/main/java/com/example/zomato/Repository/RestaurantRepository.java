package com.example.zomato.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zomato.Entity.Item;
import com.example.zomato.Entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	
	List<Restaurant> findByAddressCityIgnoreCase(String city);



}
//findbyitemnameandrestaddrcity
//	List<Item> findByItemNameAndRestAddressCity(String itemname);
//	List<Restaurant> findByNameAndAddressCity(String name, String city) ;

//	List<Restaurant> findByAddressCity(String city);

	


