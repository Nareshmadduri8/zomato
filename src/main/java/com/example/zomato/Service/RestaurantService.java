package com.example.zomato.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import com.example.zomato.DTO.ItemDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.DTO.RestaurantDto;
import com.example.zomato.Entity.Item;
import com.example.zomato.Entity.Restaurant;
import com.example.zomato.Exception.RestaurantNotFoundException;
import com.example.zomato.Repository.ItemRepository;
import com.example.zomato.Repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	

	public void saverestaurant(RestaurantDto rdto) {
		Restaurant r=new Restaurant();
		r.setName(rdto.getName());
		r.setType(rdto.getType());
		r.setMobno(rdto.getMobno());
		r.setGmail(rdto.getGmail());
		restaurantRepository.save(r);
		
	}
	
//	public ResponseStructure<Restaurant> findrestaurant(int id) {
//	Optional<Restaurant> restopt= restaurantRepository.findById(id);
//	ResponseStructure<Restaurant> rs=new ResponseStructure<Restaurant>();
//	if(restopt.isPresent()) {
//		Restaurant r=restopt.get();
//		rs.setStatuscode(HttpStatus.FOUND.value());
//		rs.setMessage("Restaurant with id :"+id+" FOUND");
//		rs.setData(r);
//		return rs;		
//	}else {
//		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
//		rs.setMessage("Restaurant with id :"+id+" NOT FOUND");
//		rs.setData(null);
//		return rs;
//	}
//	}
	
	public ResponseStructure<Restaurant> findrestaurant(int id) {
		Restaurant rest= restaurantRepository.findById(id).orElseThrow(()-> new RestaurantNotFoundException());
		ResponseStructure<Restaurant> rs=new ResponseStructure<Restaurant>();
		Restaurant r=new Restaurant();
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setMessage("Restaurant with id :"+id+" FOUND");
		rs.setData(r);
		return rs;
	}

	public ResponseStructure<Restaurant> deleterestaurant(int id) {
		Restaurant rest=restaurantRepository.findById(id).orElseThrow(()-> new RestaurantNotFoundException());
		ResponseStructure<Restaurant> rs=new ResponseStructure<Restaurant>();
		Restaurant r=new Restaurant();
			restaurantRepository.deleteById(id);
			rs.setStatuscode(HttpStatus.OK.value());
			rs.setMessage("Restaurant with id :"+id+" deleted");
			rs.setData(null);
		return rs;
	}

	
	
	
	
	
	
	
}
