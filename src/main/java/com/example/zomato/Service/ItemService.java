package com.example.zomato.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.zomato.DTO.ItemDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.Entity.Item;
import com.example.zomato.Entity.Restaurant;
import com.example.zomato.Exception.RestaurantNotFoundException;
import com.example.zomato.Repository.ItemRepository;
import com.example.zomato.Repository.RestaurantRepository;

@Service
public class ItemService {

	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;

	
	public ResponseStructure<Restaurant> saveitem(int id,ItemDto itemDto) {
		ResponseStructure<Restaurant> rs=new ResponseStructure<Restaurant>();
		Restaurant r=restaurantRepository.findById(id).orElseThrow(()-> new RestaurantNotFoundException());
		Item i=new Item();
		i.setName(itemDto.getName());
		i.setType(itemDto.getType());
		i.setPrice(itemDto.getPrice());
		i.setQuantity(itemDto.getQuantity());
		i.setDescription(itemDto.getDescription());
		i.setCategory(itemDto.getCategory());
		i.setAvailability(itemDto.getAvailability());
		r.getMenu().add(i);
		i.setRestaurant(r);
		itemRepository.save(i);
		rs.setStatuscode(HttpStatus.ACCEPTED.value());
		rs.setMessage("RESTAURANT ITEMS SAVED");
		rs.setData(restaurantRepository.save(r));
		return rs;
	}
	

}
