package com.example.zomato.Service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.zomato.DTO.CustomerDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.Entity.Address;
import com.example.zomato.Entity.Customer;
import com.example.zomato.Entity.Item;
import com.example.zomato.Entity.Restaurant;
import com.example.zomato.Exception.CustomerNotFoundException;
import com.example.zomato.Exception.RestaurantNotFoundException;
import com.example.zomato.Repository.CustomerRepository;
import com.example.zomato.Repository.RestaurantRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private RestaurantRepository restaurantRepo;

	public ResponseStructure<Customer> createaccount(CustomerDto cdto) {
		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		Customer c = new Customer();
		c.setName(cdto.getName());
		c.setMobno(cdto.getMobno());
		c.setGmail(cdto.getGmail());
		c.setAge(cdto.getAge());
		c.setGender(cdto.getGender());
		customerRepo.save(c);
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setMessage("Customer saved successfully");
		rs.setData(c);
		return rs;
	}

	public ResponseStructure<Customer> findcustomer(int id) {
		Optional<Customer> cust = customerRepo.findById(id);
		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		if (cust.isPresent()) {
			Customer c = cust.get();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setMessage("Customer with id :" + id + " Found");
			rs.setData(c);
			return rs;
		} else {
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Customer with id :" + id + "NOT FOUND");
			rs.setData(null);
			return rs;
		}
	}

	public ResponseStructure<Customer> deletecustomeracc(int id) {
		Optional<Customer> customeropt = customerRepo.findById(id);
		ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
		if (customeropt.isPresent()) {
			Customer c = customeropt.get();
			customerRepo.deleteById(id);
			rs.setStatuscode(HttpStatus.OK.value());
			rs.setMessage("Customer with id :" + id + "Deleted");
			rs.setData(c);
		} else {
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Customerwith id :" + id + "NOT FOUND");
			rs.setData(null);
		}
		return rs;
	}

	

//	Search Restaurant 

//	public ResponseStructure<List<Restaurant>> findItemAndRestaurant(int cid, String restname) {
//		ResponseStructure<List<Restaurant>> rs = new ResponseStructure<>();
//		Customer cust = customerRepo.findById(cid).orElseThrow(() -> new RestaurantNotFoundException());
//		List<Address> custaddress = cust.getAddress();
//		if (!custaddress.isEmpty()) {
////			Address custAddress = custaddress.get(0);
////			String city = custAddress.getCity();
//			String city = custaddress.get(0).getCity();
////			List<Restaurant> restaurants = RestaurantRepository.findByNameAndAddressCity(name, city);
//			List<Restaurant> restaurants = RestaurantRepo.findByAddressCityIgnoreCase(city);
//			List<Restaurant> result = restaurants.stream().filter(r -> r.getName() != null)
//					.filter(r -> r.getName().toLowerCase().contains(restname.toLowerCase())).toList();
//			
//			rs.setStatuscode(HttpStatus.OK.value());
//			rs.setMessage("Restaurants found");
//			System.out.println("Restaurants found: " + result.size());
//			rs.setData(result);
//
//		} else {
//			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
//			rs.setMessage("Customer address not found");
//			rs.setData(null);
//		}
//		return rs;
//	}

// Search items and Restaurant
	
	public ResponseStructure<List<Restaurant>> findByItemNameAndRestAddCity(int cid, String name) {
		ResponseStructure<List<Restaurant>>rs=new ResponseStructure<List<Restaurant>>();
		Customer cust=customerRepo.findById(cid).orElseThrow(()-> new CustomerNotFoundException());
		List<Address> custaddress=cust.getAddress();
		if(!custaddress.isEmpty()) {
			String city=custaddress.get(0).getCity();
			List<Restaurant> restaurants= restaurantRepo.findByAddressCityIgnoreCase(city);
			List<Restaurant> result = restaurants.stream()
	                .filter(restaurant ->
	                        (restaurant.getName() != null
	                                && restaurant.getName().toLowerCase()
	                                        .contains(name.toLowerCase()))
	                                ||
	                        (restaurant.getMenu() != null
	                                && restaurant.getMenu().stream()
	                                        .anyMatch(item ->
	                                                item.getName() != null
	                                                        && item.getName().toLowerCase()
	                                                                .contains(name.toLowerCase()))
	                ))
	                .toList();
			if(!result.isEmpty()) {
				rs.setStatuscode(HttpStatus.OK.value());
				rs.setMessage("Restaurants Found");
				rs.setData(result);
			}else {
				rs.setStatuscode(HttpStatus.NOT_FOUND.value());
				rs.setMessage("No restaurants found");
				rs.setData(null);
			}
		}else {
				 rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			        rs.setMessage("Customer address not found");
			        rs.setData(null);
			}
			return rs;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public ResponseStructure<List<Restaurant>> findByItemNameAndRestAddCity(int cid, String itemname) {
//		ResponseStructure<List<Restaurant>> rs = new ResponseStructure<>();
//		Customer cust = 
//				customerRepo.findById(cid).orElseThrow(() -> new RestaurantNotFoundException());
//		List<Address> custaddress = cust.getAddress();
//		if (!custaddress.isEmpty()) {
//			String city = custaddress.get(0).getCity();
//			System.out.println("Customer City: " + city);
//			List<Restaurant> restaurants =
//					RestaurantRepo.findByAddressCityIgnoreCase(city);
//			System.out.println("Restaurants Count: " + restaurants.size());
//			System.out.println("Search Text: " + itemname);
//			 List<Restaurant> result = restaurants.stream()
//		                .filter(r ->
//		                    // Restaurant name matches
//		                    (r.getName() != null &&
//		                     r.getName().toLowerCase()
//		                      .contains(itemname.toLowerCase()))
//
//		                    ||
//
//		                    // Any item name matches
//		                    (r.getMenu() != null &&
//		                     r.getMenu().stream()
//		                      .anyMatch(i ->
//		                          i.getName() != null &&
//		                          i.getName().toLowerCase()
//		                           .contains(itemname.toLowerCase())))
//		                )
//		                .toList();
//
//			 	System.out.println("Result Count: " + result.size());
//			rs.setStatuscode(HttpStatus.OK.value());
//			rs.setMessage("Restaurants found");
//			rs.setData(restaurants);
//
//		} else {
//			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
//			rs.setMessage("Customer address not found");
//			rs.setData(null);
//		}
//		return rs;
//	}

//	public ResponseStructure<List<Restaurant>> findByItemNameAndRestAddCity(int cid , String itemname) {
//	    ResponseStructure<List<Restaurant>> rs = new ResponseStructure<>();
//	    Customer cust = customerRepo.findById(cid).orElseThrow();
//	    if(cust!=null) {
//	    	throw new CustomerNotFoundException();
//	    }
//	    List<Address> custaddress = cust.getAddress();
//	    if (custaddress.isEmpty()) {
//	        rs.setStatuscode(HttpStatus.NOT_FOUND.value());
//	        rs.setMessage("Customer address not found");
//	        rs.setData(null);
//	        return rs;
//	    }
//	    String city = custaddress.stream()
//	            .map(Address::getCity)
//	            .filter(c -> c != null && !c.isBlank())
//	            .findFirst()
//	            .orElse(null);
//
//	    if (city == null) {
//	        rs.setStatuscode(HttpStatus.NOT_FOUND.value());
//	        rs.setMessage("No valid city found");
//	        rs.setData(null);
//	        return rs;
//	    }
//	    System.out.println("Customer City = " + city);
//	    List<Restaurant> restaurants =
//	            RestaurantRepo.findByAddressCityIgnoreCase(city);
//	    List<Restaurant> result = restaurants.stream().filter(r -> {
//
//	                // Search by Restaurant Name
//	                boolean restaurantMatch =
//	                        r.getName() != null &&
//	                        r.getName().toLowerCase()
//	                                .contains(itemname.toLowerCase());
//
//	                // Search by Item Name
//	                boolean itemMatch =
//	                        r.getMenu() != null &&
//	                        r.getMenu().stream()
//	                                .anyMatch(item ->
//	                                        item.getName() != null &&
//	                                        item.getName().toLowerCase()
//	                                                .contains(itemname.toLowerCase()));
//
//	                // Return restaurant if either matches
//	                return restaurantMatch || itemMatch;
//	            })
//	            .toList();
//	    System.out.println("Restaurants found = " + result.size());
//	    rs.setStatuscode(HttpStatus.OK.value());
//	    rs.setMessage("Restaurants found");
//	    rs.setData(result);
//	    return rs;
//	}
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


