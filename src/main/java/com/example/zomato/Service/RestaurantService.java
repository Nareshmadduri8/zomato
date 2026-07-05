package com.example.zomato.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.geo.Point;

import com.example.zomato.DTO.ItemDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.DTO.RestaurantDto;
import com.example.zomato.Entity.Address;
import com.example.zomato.Entity.Coordinates;
import com.example.zomato.Entity.Item;
import com.example.zomato.Entity.Order;
import com.example.zomato.Entity.Restaurant;
import com.example.zomato.Exception.OrderNotFoundException;
import com.example.zomato.Exception.RestaurantNotFoundException;
import com.example.zomato.Repository.ItemRepository;
import com.example.zomato.Repository.OrderRepository;
import com.example.zomato.Repository.RestaurantRepository;



@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Autowired
	private OrderRepository orderRepository;
	

	public ResponseStructure<Restaurant> saverestaurant(RestaurantDto restaurantDto) {
		ResponseStructure<Restaurant> rs=new ResponseStructure<Restaurant>();
		Restaurant restaurant=new Restaurant();
		restaurant.setName(restaurantDto.getName());
		restaurant.setType(restaurantDto.getType());
		restaurant.setMobno(restaurantDto.getMobno());
		restaurant.setGmail(restaurantDto.getGmail());
		restaurant.setAvailability("open");
		String url = "https://us1.locationiq.com/v1/reverse?key=pk.3cdc3a5a72f82bb33771f7551a92c22a&lat="
				+ restaurantDto.getLatitude() + "&lon=" + restaurantDto.getLongitude() + "&format=json&";
		
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
		restaurant.setAddress(restaddress);
		
		Coordinates coordinates=new Coordinates();
		coordinates.setLatitude(restaurantDto.getLatitude());
		coordinates.setLongitude(restaurantDto.getLongitude());
		restaddress.setCoordinates(coordinates);
		restaurantRepository.save(restaurant);
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setMessage("RESTAURANT SAVED");
		rs.setData(restaurant);
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
		Restaurant restaurant= restaurantRepository.findById(id).orElseThrow(()-> new RestaurantNotFoundException());
		ResponseStructure<Restaurant> rs=new ResponseStructure<Restaurant>();
		Restaurant r=new Restaurant();
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setMessage("Restaurant with id :"+id+" FOUND");
		rs.setData(r);
		return rs;
	}

	public ResponseStructure<Restaurant> deleterestaurant(int id) {
		Restaurant restaurant=restaurantRepository.findById(id).orElseThrow(()-> new RestaurantNotFoundException());
		ResponseStructure<Restaurant> rs=new ResponseStructure<Restaurant>();
		Restaurant r=new Restaurant();
			restaurantRepository.deleteById(id);
			rs.setStatuscode(HttpStatus.OK.value());
			rs.setMessage("Restaurant with id :"+id+" deleted");
			rs.setData(null);
		return rs;
	}

	public ResponseStructure<List<Order>> getplacedorder(int restaurantid) {
		ResponseStructure<List<Order>> rs=new ResponseStructure<List<Order>>();
		Restaurant restaurant= restaurantRepository.findById(restaurantid).orElseThrow(()-> new RestaurantNotFoundException());
		List<Order> currentrestaurantorder=restaurant.getActiveorders();
		List<Order> placedorder=new ArrayList<>();
		for (Order orders : currentrestaurantorder) {
			if(orders.getStatus().contains("Placed")) {
				placedorder.add(orders);
			}
		}
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("This are the orders");
		rs.setData(placedorder);
		return rs;
	}

	

	private static final String GEO_KEY ="deliverypartner-locations";
		public List<Long> acceptOrder(int orderid) {
			Order order = orderRepository.findById(orderid)
		            .orElseThrow(() -> new OrderNotFoundException());
		    Restaurant restaurant = order.getRestaurant();

		    // Check whether order is in PLACED status
		    if (order.getStatus().contains("PLACED")) {
		        throw new RuntimeException( "Order is already accepted" );
		    }
		  
		    // Accept the order
		    order.setStatus("ORDER ACCEPTED");

		    // Save order
		    orderRepository.save(order);

		    // Get restaurant coordinates from database
		    Coordinates coordinates = restaurant
		            .getAddress()
		            .getCoordinates();

		    // Redis GEO operations
		    GeoOperations<String, String> geoOperations =
		            redisTemplate.opsForGeo();

		    // Search delivery partners within given range
		    GeoResults<RedisGeoCommands.GeoLocation<String>> results =
		            geoOperations.radius(
		                    GEO_KEY,
		                    new Circle(
		                          new Point(coordinates.getLongitude(),
		                                    coordinates.getLatitude()
		                            ),
		                            new Distance(50, Metrics.KILOMETERS)
		                    )
		            );

		    // If no delivery partners found

		    if (results == null || results.getContent().isEmpty()) {
		        System.out.println("No nearby delivery partners found.");
		        return Collections.emptyList();
		    }
		    // Convert Redis results into partner IDs
		    List<Long> availableDps = results
		            .getContent()
		            .stream()
		            .map(result ->
		                    Long.parseLong(
		                            result
		                                    .getContent()
		                                    .getName()
		                    )
		            )
		            .toList();
		    return availableDps;
		}
		
	}
	
