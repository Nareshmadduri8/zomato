package com.example.zomato.Service;

import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.zomato.DTO.CustomerDto;
import com.example.zomato.DTO.GetCartDto;
import com.example.zomato.DTO.PlaceOrderDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.Entity.Address;
import com.example.zomato.Entity.CartItem;
import com.example.zomato.Entity.Customer;
import com.example.zomato.Entity.Item;
import com.example.zomato.Entity.Order;
import com.example.zomato.Entity.Restaurant;
import com.example.zomato.Exception.CartIsEmptyException;
import com.example.zomato.Exception.CustomerAddressNotFound;
import com.example.zomato.Exception.CustomerNotFoundException;
import com.example.zomato.Exception.EmptyCartException;
import com.example.zomato.Exception.ItemNotAvailableException;
import com.example.zomato.Exception.ItemNotFoundException;
import com.example.zomato.Exception.RestaurantNotAvailableAtMomentException;
import com.example.zomato.Exception.RestaurantNotMatchingException;
import com.example.zomato.Exception.RestaurantNotFoundException;
import com.example.zomato.Repository.AddressRepository;
import com.example.zomato.Repository.CustomerRepository;
import com.example.zomato.Repository.ItemRepository;
import com.example.zomato.Repository.OrderRepository;
import com.example.zomato.Repository.RestaurantRepository;
import com.sun.jdi.DoubleValue;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private RestaurantRepository restaurantRepo;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private AddressRepository addressRepository;

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
		ResponseStructure<List<Restaurant>> rs = new ResponseStructure<List<Restaurant>>();
		Customer cust = customerRepo.findById(cid).orElseThrow(() -> new CustomerNotFoundException());
		List<Address> custaddress = cust.getAddress();
		if (!custaddress.isEmpty()) {
			String city = custaddress.get(0).getCity();
			List<Restaurant> restaurants = restaurantRepo.findByAddressCityIgnoreCase(city);
			List<Restaurant> result = restaurants
					.stream().filter(
							restaurant -> (restaurant.getName() != null
									&& restaurant.getName().toLowerCase().contains(name.toLowerCase()))
									|| (restaurant.getMenu() != null && restaurant.getMenu().stream()
											.anyMatch(item -> item.getName() != null
													&& item.getName().toLowerCase().contains(name.toLowerCase()))))
					.toList();
			if (!result.isEmpty()) {
				rs.setStatuscode(HttpStatus.OK.value());
				rs.setMessage("Restaurants Found");
				rs.setData(result);
			} else {
				rs.setStatuscode(HttpStatus.NOT_FOUND.value());
				rs.setMessage("No restaurants found");
				rs.setData(null);
			}
		} else {
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Customer address not found");
			rs.setData(null);
		}
		return rs;
	}

//		Add items to cart 
	public ResponseStructure<Item> additemtocart(int cid, int itemid) {
		ResponseStructure<Item> rs = new ResponseStructure<Item>();
		Customer customer = customerRepo.findById(cid).orElseThrow(() -> new CustomerNotFoundException());
		Item item = itemRepository.findById(itemid).orElseThrow(() -> new ItemNotFoundException());
		System.out.println(item.getRestaurant());
		if (!"Available".equals(item.getAvailability())) {
			throw new ItemNotAvailableException();
		}
		List<CartItem> cart = customer.getCart();

		// Handle empty cart
		if (cart.isEmpty()) {
			CartItem cartItem = new CartItem();
			cartItem.setItem(item);
			cartItem.setQuantity(1);
			cart.add(cartItem);
		} else {

			if (item.getRestaurant().getId() != cart.get(0).getItem().getRestaurant().getId()) {
				throw new RestaurantNotMatchingException();
			}
			boolean found = false;
			for (CartItem citem : cart) {
				if (item.getId() == citem.getItem().getId()) {
					citem.setQuantity(citem.getQuantity() + 1);
					found = true;
					break;
				}
			}
			if (!found) {
				CartItem cartItem = new CartItem();
				cartItem.setItem(item);
				cartItem.setQuantity(1);
				cart.add(cartItem);
			}
		}

		customerRepo.save(customer);
		rs.setStatuscode(HttpStatus.ACCEPTED.value());
		rs.setMessage("Add to cart item successfully");
		rs.setData(item);
		return rs;
	}

//		Get cart 
	public ResponseStructure<GetCartDto> getcart(int cid) {
		GetCartDto getCartDto = new GetCartDto();
		Customer customer = customerRepo.findById(cid).orElseThrow(() -> new CustomerNotFoundException());
		if (customer.getCart().size() == 0) {
			throw new EmptyCartException();
		}
		List<CartItem> cart = customer.getCart();
		Restaurant restaurant = cart.get(0).getItem().getRestaurant();
		double packagingfee = restaurant.getPackagingfees();
		double totalItemPrice = 0;
		for (CartItem cartItem : cart) {
			totalItemPrice += cartItem.getItem().getPrice() * cartItem.getQuantity();
		}
//	 	Distance take from locationIQ between Restaurant and customer
		Address restadd = restaurant.getAddress();
		Address customerAddress = customer.getAddress().get(0);
		if (customerAddress.getCoordinates() == null) {
		    throw new RuntimeException(
		            "Coordinates not found for customer address ID : "
		                    + customerAddress.getId());
		}
		double restLatitude = restadd.getCoordinates().getLatitude();
		double restLongitude = restadd.getCoordinates().getLongitude();
		double custlat = customerAddress.getCoordinates().getLatitude();
		double custlon = customerAddress.getCoordinates().getLongitude();
		String url = "https://us1.locationiq.com/v1/directions/driving/" + custlat + "," + custlon + ";"
				+ restLatitude + "," + restLongitude + "?"
				+ "key=pk.9a854214837ecde37e67b717d9a839cc&steps=true&alternatives=true&geometries=polyline&overview=full&";
		
		Map<String, Object> response = (Map<String, Object>) restTemplate.getForObject(url, Object.class);
		System.out.println("FULL RESPONSE = " + response);
		List<Object> routes=(List<Object>) response.get("routes");
		Map<String, Object> route=(Map<String, Object>)routes.get(0);
//		Time take from locationIQ website
		double estimatedTime = ((Number) route.get("duration")).doubleValue() / 60.0;
		double distance = ((Number) route.get("distance")).doubleValue() / 1000.0;
		double deliveryCharges = 0;
		if (distance > 5) {
			deliveryCharges = (distance - 5) * 10;
		}
		double totaltopay = totalItemPrice + deliveryCharges + packagingfee;
		getCartDto.setRestaurant(restaurant);
		getCartDto.setCartItems(cart);
		getCartDto.setPackagingFee(20);
		getCartDto.setTotalItemPrice(totalItemPrice);
		getCartDto.setEstimatedTime(estimatedTime);
		getCartDto.setDeliveryCharges(deliveryCharges);
		getCartDto.setDistance(distance);
		getCartDto.setTotalToPay(totaltopay);
		customer.setCurrentcartdeliverycharges(deliveryCharges);
		customer.setTotalcartvalue(totaltopay);
		customerRepo.save(customer);
		ResponseStructure<GetCartDto> rs = new ResponseStructure<GetCartDto>();
		rs.setStatuscode(HttpStatus.ACCEPTED.value());
		rs.setMessage("This are the item in the cart");
		rs.setData(getCartDto);
		return rs;
	}
	
//	place the order 
	public ResponseStructure<Order> placetheorder(int cid, int customeraddressid) {
		ResponseStructure<Order> rs=new ResponseStructure<Order>();
		Item item=new Item();
		Customer customer= customerRepo.findById(cid)
                   .orElseThrow(()-> new CustomerNotFoundException());
		if(customer.getCart().size()==0) {
			throw new EmptyCartException();
		}
		List<CartItem> cartitem= new ArrayList<>(customer.getCart()) ;
		Restaurant restaurant= cartitem.get(0).getItem().getRestaurant();
		if(restaurant.getAvailability().equals("closed")) {
			throw new RestaurantNotAvailableAtMomentException();
		}
		//fnding the availibity for the items
		cartitem.forEach(cartItem->{
			if(!"Available".equals(cartItem.getItem().getAvailability())) {
				throw new ItemNotAvailableException();
			}
		});
		Order order=new Order();
		order.setCustomer(customer);
		order.setRestaurant(restaurant);
		order.setItems(cartitem);
		order.setComment("Come fast");
		order.setStatus("Placed");
		order.setPickupaddress(restaurant.getAddress());
		
		Address deliveryaddress=addressRepository.findById(customeraddressid).orElseThrow(()->new CustomerAddressNotFound());
		order.setDeliveryorderaddress(deliveryaddress);
		
		order.setDate(LocalDate.now().toString());
		order.setTime(LocalTime.now().toString());
		order.setDeliverycharges(customer.getCurrentcartdeliverycharges());
		order.setTotalamount(customer.getTotalcartvalue());
		orderRepository.save(order);
		customer.getActiveorders().add(order);
		customerRepo.save(customer);
		restaurant.getActiveorders().add(order);
		restaurantRepo.save(restaurant);
		customer.getCart().clear();
		customerRepo.save(customer);
		rs.setStatuscode(HttpStatus.ACCEPTED.value());
		rs.setMessage(" ORDER PLACED SUCCESSFULLY");
		rs.setData(order);
		return rs;
	}
	}	
		
		
		





		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		





































































