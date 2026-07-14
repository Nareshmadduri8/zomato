package com.example.zomato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.example.zomato.Entity.Order;
import com.example.zomato.Entity.Restaurant;
import com.example.zomato.Service.ItemService;
import com.example.zomato.Service.RestaurantService;

import jakarta.validation.Valid;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	
	@PostMapping("/restaurant/createrestaurant")
	public ResponseStructure<Restaurant> saverestaurantdto(@Valid @RequestBody RestaurantDto rdto) {
		return restaurantService.saverestaurant(rdto);
	}
	
	
	@GetMapping("/restaurant/findrestaurant")
	public ResponseStructure<Restaurant> findrestaurant(@RequestParam int id) {
		return restaurantService.findrestaurant(id);
	}
	
	@DeleteMapping("/restaurant/deleterestaurant")
	public ResponseStructure<Restaurant> deleterestaurant(@RequestParam int id) {
		return restaurantService.deleterestaurant(id);
	}
	
	@GetMapping("/restaurant/getplacedorder")
	public ResponseStructure<List<Order>> getplacedorder(@RequestParam int restaurantid) {
		return restaurantService.getplacedorder(restaurantid);
	}
	
	@PostMapping("/restaurant/acceptorder")
	public List<Long> restaurantAcceptorder(@RequestParam int orderid) {
		return restaurantService.acceptOrder(orderid);
	}
	
	
	

	

	
}
