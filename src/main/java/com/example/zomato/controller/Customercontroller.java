package com.example.zomato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.zomato.DTO.CustomerDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.Entity.Coordinates;
import com.example.zomato.Entity.Customer;
import com.example.zomato.Entity.Item;
import com.example.zomato.Entity.Restaurant;
import com.example.zomato.Service.Addressservice;
import com.example.zomato.Service.CustomerService;

@RestController
public class Customercontroller {

	@Autowired
	private CustomerService customerservice;
	
	@Autowired
	private Addressservice addressservice;
	
	@PostMapping("/customer/createaccount")
	public ResponseStructure<Customer> savecustomerdto(@RequestBody CustomerDto cdto) {
		return customerservice.createaccount(cdto);
	}
	
	@GetMapping("/customer/findcustomer")
	public ResponseStructure<Customer> findcustomerdto(@RequestParam int id) {
		return customerservice.findcustomer(id);
	}
	
	@DeleteMapping("/customer/deleteaccount")
	public ResponseStructure<Customer> deletecustomeracc(@RequestParam int id) {
		return customerservice.deletecustomeracc(id);
	}
	
	
//	Creating new address for the customer
	@PostMapping("/customer/addnewaddress/{id}")
	public void createnewaddress(@RequestBody Coordinates coordinates,@PathVariable int id) {
		addressservice.createnewaddress(coordinates,id);
	}
	
//	@GetMapping("/customer/search/itemandrestaurant/{cid}/{restname}")
//	public ResponseStructure<List<Restaurant>> findItemAndRestaurant(@PathVariable int cid,@PathVariable String restname) {
//		return customerservice.findItemAndRestaurant(cid,restname);
//	}
//	
	@GetMapping("/cust/search/itemsandrestaurant/{cid}/{name}")
	public ResponseStructure<List<Restaurant>> findByItemNameAndRestAddCity(@PathVariable int cid, @PathVariable String name) {
		return customerservice.findByItemNameAndRestAddCity(cid,name);
	}
	
	@PostMapping("/customer/additemtocart/{cid}/{itemid}")
	public ResponseStructure<Item> additemtocart(@PathVariable int cid, @PathVariable int itemid) {
		return customerservice.additemtocart(cid,itemid);
	}
	
	@PostMapping("/customer/getcart/{cid}")
	public void placetheorders(@PathVariable int cid) {
		customerservice.placetheorder(cid);
	}
	
}

