package com.example.zomato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.zomato.DTO.ItemDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.DTO.RestaurantDto;
import com.example.zomato.Entity.Restaurant;
import com.example.zomato.Service.ItemService;
import com.example.zomato.Service.RestaurantService;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	
	@PostMapping("/rest/createrestaurant")
	public void saverestaurantdto(@RequestBody RestaurantDto rdto) {
		restaurantService.saverestaurant(rdto);
	}
	
	@GetMapping("/rest/findrestaurant")
	public ResponseStructure<Restaurant> findrestaurant(@RequestParam int id) {
		return restaurantService.findrestaurant(id);
	}
	
	@DeleteMapping("/rest/deleterestaurant")
	public ResponseStructure<Restaurant> deleterestaurant(@RequestParam int id) {
		return restaurantService.deleterestaurant(id);
	}
	
	
	

	
}
