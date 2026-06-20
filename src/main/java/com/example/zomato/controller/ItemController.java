package com.example.zomato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.zomato.DTO.ItemDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.Entity.Restaurant;
import com.example.zomato.Service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	
	@PostMapping("/save/itemdto/{id}")
	public ResponseStructure<Restaurant> saveitemdto(@PathVariable int id,@RequestBody ItemDto itemDto ) {
		return itemService.saveitem(id,itemDto);
	}
}
