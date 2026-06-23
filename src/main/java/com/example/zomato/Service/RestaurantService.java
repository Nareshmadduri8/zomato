package com.example.zomato.Service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.client.RestTemplate;

import com.example.zomato.DTO.ItemDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.DTO.RestaurantDto;
import com.example.zomato.Entity.Address;
import com.example.zomato.Entity.Coordinates;
import com.example.zomato.Entity.Item;
import com.example.zomato.Entity.Restaurant;
import com.example.zomato.Exception.RestaurantNotFoundException;
import com.example.zomato.Repository.ItemRepository;
import com.example.zomato.Repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	

	public ResponseStructure<Restaurant> saverestaurant(RestaurantDto rdto) {
		ResponseStructure<Restaurant> rs=new ResponseStructure<Restaurant>();
		Restaurant r=new Restaurant();
		r.setName(rdto.getName());
		r.setType(rdto.getType());
		r.setMobno(rdto.getMobno());
		r.setGmail(rdto.getGmail());
		r.setAvailability("closed");
		String url = "https://us1.locationiq.com/v1/reverse?key=pk.3cdc3a5a72f82bb33771f7551a92c22a&lat="
				+ rdto.getLatitude() + "&lon=" + rdto.getLongitude() + "&format=json&";
		Map<String, Object> response = (Map<String, Object>) restTemplate.getForObject(url, Object.class);
		Map<String, Object> address = (Map<String, Object>) response.get("address");
		System.out.println(address);
		Address restaddress = new Address();
		restaddress.setCity((String) address.get("city"));
		restaddress.setCountry((String) address.get("country"));
		restaddress.setLandmark((String) address.get("neighbourhood"));
		restaddress.setPincode(Integer.parseInt((String) address.get("postcode")));
		restaddress.setState((String) address.get("state"));
		restaddress.setStreet((String) address.get("road"));
		r.setAddress(restaddress);
		Coordinates coordinates=new Coordinates();
		coordinates.setLatitude(rdto.getLatitude());
		coordinates.setLongitude(rdto.getLongitude());
		restaddress.setCoordinates(coordinates);
		restaurantRepository.save(r);
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setMessage("RESTAURANT SAVED");
		rs.setData(r);
		return rs;
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
