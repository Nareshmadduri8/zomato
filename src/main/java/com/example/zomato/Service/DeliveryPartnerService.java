package com.example.zomato.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.zomato.DTO.DeliveryPartnerDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.Entity.Coordinates;
import com.example.zomato.Entity.DeliveryPartner;
import com.example.zomato.Entity.Order;
import com.example.zomato.Entity.Restaurant;
import com.example.zomato.Exception.DeliveryPartnerNotFoundExce;
import com.example.zomato.Exception.OrderNotFoundException;
import com.example.zomato.Exception.OrderNotPickedByDeliveryPartner;
import com.example.zomato.Exception.RestaurantNotFoundException;
import com.example.zomato.Repository.DeliveryPartnerRepository;
import com.example.zomato.Repository.OrderRepository;

@Service
public class DeliveryPartnerService {

	@Autowired
	private DeliveryPartnerRepository deliveryPartnerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;


	public void savedeliverypartner(DeliveryPartnerDto dpdto) {
		DeliveryPartner dp = new DeliveryPartner();
		dp.setName(dpdto.getName());
		dp.setMobno(dpdto.getMobno());
		dp.setGmail(dpdto.getGmail());
		dp.setVehicleNo(dpdto.getVehicleNo());
		deliveryPartnerRepository.save(dp);
	}

// FINDING RESTAURANT BY IF AND ELSE METHOD

//	public ResponseStructure<DeliveryPartner> finddeliverypartner(int id) {
//		Optional<DeliveryPartner> dpopt=dpRepository.findById(id);
//		ResponseStructure<DeliveryPartner> rs=new ResponseStructure<DeliveryPartner>();
//		if(dpopt.isPresent()) {
//			DeliveryPartner dp=dpopt.get();
//			 rs.setStatuscode(HttpStatus.FOUND.value());
//			 rs.setMessage("DeliveryPartner with id :"+id+" Found");
//			 rs.setData(dp);
//			 }else {
//				 rs.setStatuscode(HttpStatus.NOT_FOUND.value());
//					rs.setMessage("Customer with id :"+id+" NOT FOUND");
//					rs.setData(null);
//			 }
//		return rs; 
//		}

//	FINDING RESTAURANT BY USING ORELSETHORW METHOD

	public ResponseStructure<DeliveryPartner> finddeliverypartner(int id) {
		DeliveryPartner dp = deliveryPartnerRepository.findById(id).orElseThrow(() -> new DeliveryPartnerNotFoundExce());
		ResponseStructure<DeliveryPartner> rs = new ResponseStructure<DeliveryPartner>();
		DeliveryPartner d = new DeliveryPartner();
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setMessage("DeliveryPartner with id :" + id + " Found");
		rs.setData(d);
		return rs;
	}

//	public ResponseStructure<DeliveryPartner> deletedeliverypartner(int id) {
//		Optional<DeliveryPartner> dpopt=dpRepository.findById(id);
//		ResponseStructure<DeliveryPartner> rs=new ResponseStructure<DeliveryPartner>();
//		if(dpopt.isPresent()) {
//			DeliveryPartner dp=dpopt.get();
//			dpRepository.deleteById(id);
//			 rs.setStatuscode(HttpStatus.OK.value());
//		     rs.setMessage("DeliveryPartner with id " + id + " Deleted");
//		     rs.setData(dp);
//		    } else {
//		        rs.setStatuscode(HttpStatus.NOT_FOUND.value());
//		        rs.setMessage("DeliveryPartner with id " + id + " NOT FOUND");
//		        rs.setData(null);
//		    }
//		    return rs;
//		}
	
	
	
//	DELETING RESTAURANT BY USING ORELSETHORW METHOD

	public ResponseStructure<DeliveryPartner> deletedeliverypartner(int id) {
		DeliveryPartner deliveryPartner= deliveryPartnerRepository.findById(id)
				.orElseThrow(()-> new DeliveryPartnerNotFoundExce());
		ResponseStructure<DeliveryPartner> rs = new ResponseStructure<DeliveryPartner>();
		DeliveryPartner dp=new DeliveryPartner();
		deliveryPartnerRepository.deleteById(id);
		 rs.setStatuscode(HttpStatus.OK.value());
	     rs.setMessage("DeliveryPartner with id " + id + " Deleted");
	     rs.setData(dp);
	     return rs;
	}
	
	
//	Sending delivery partner coordinates to Redis
	

	private static final String GEO_KEY ="deliverypartner-locations";

	public void updateLocation(int deliveryPartnerId, double latitude, double longitude) {
		
		String key = "deliveryPartner:" + deliveryPartnerId;

        redisTemplate.opsForHash().put(key, "latitude", String.valueOf(latitude));
        redisTemplate.opsForHash().put(key, "longitude", String.valueOf(longitude));
        
        redisTemplate.opsForGeo().add(
                GEO_KEY,
                new Point(longitude, latitude),
                String.valueOf(deliveryPartnerId)
        );
	}
	
	public List<Order> getAssignedOrders(Long deliverypartnerId) {
		
		String deliverypartnerkey = "deliveryPartner:" + deliverypartnerId;

	    Map<Object, Object> entries =
	            redisTemplate.opsForHash().entries(deliverypartnerkey);

	    if (entries == null || entries.isEmpty()) {
	        return Collections.emptyList();
	    }

	    List<Order> ordersList = new ArrayList<>();

	    for (Map.Entry<Object, Object> entry : entries.entrySet()) {

	        String field = entry.getKey().toString();

	        // Ignore latitude and longitude
	        // Read only order fields
	        if (field.startsWith("order:")) {

	            int orderId = Integer.parseInt(
	                    entry.getValue().toString()
	            );

	            Order order = orderRepository.findById(orderId).orElse(null);

	            if (order != null) {
	                ordersList.add(order);
	            }
	        }
	    }
		    return  ordersList;
		  }
	
//			Accept order by delivery partner

	private static final String GEO_KEY1 ="deliverypartner-locations";
		public Order acceptOrderByDeliveryPartner(int deliveryPartnerId, int orderid) {
		// Find order from PostgreSQL
		Order order = orderRepository.findById(orderid)
	            .orElseThrow(() -> new OrderNotFoundException());
		
	    String currentDeliveryPartnerKey = "deliveryPartner:" + deliveryPartnerId;

	    // Check whether DP exists in Redis
	    Boolean dpExists = redisTemplate.hasKey(currentDeliveryPartnerKey);

	    if (!Boolean.TRUE.equals(dpExists)) {
	        throw new RuntimeException(
	                "Delivery Partner Not Found In Redis"
	        );
	    }

	    // Check whether this order was offered to this DP
	    Boolean orderExists = redisTemplate
	            .opsForHash()
	            .hasKey(
	            		currentDeliveryPartnerKey,
	                    "order:" + orderid
	            );

	    if (!Boolean.TRUE.equals(orderExists)) {
	        throw new RuntimeException(
	                "This order was not offered to this delivery partner"
	        );
	    }

	    // Prevent accepting an already assigned order
	    if ("DELIVERY PARTNER ASSIGNED"
	            .equalsIgnoreCase(order.getStatus())) {

	        throw new RuntimeException(
	                "Order already accepted by another delivery partner"
	        );
	    }

	    // Update order status
	    order.setStatus("DELIVERY PARTNER ASSIGNED");
//	    order.setDeliveryPartner(deliveryPartner);

	    // Save order
	    orderRepository.save(order);


	    // ==========================================
	    // REMOVE ACCEPTED ORDER FROM ALL OTHER DPs
	    // ==========================================

	    Set<String> dpKeys =
	            redisTemplate.keys("deliveryPartner:*");

	    if (dpKeys != null) {

	        for (String dpKey : dpKeys) {

	            redisTemplate
	                    .opsForHash()
	                    .delete(
	                            dpKey,
	                            "order:" + orderid
	                    );
	        }
	    }


	    // ==========================================
	    // REMOVE ALL OTHER ORDERS FROM ACCEPTING DP
	    // ==========================================

	    Map<Object, Object> currentDpData =
	            redisTemplate
	                    .opsForHash()
	                    .entries(currentDeliveryPartnerKey);

	    for (Object field : currentDpData.keySet()) {

	        String fieldName = field.toString();

	        if (fieldName.startsWith("order:")) {

	            redisTemplate
	                    .opsForHash()
	                    .delete(
	                    		currentDeliveryPartnerKey,
	                            fieldName
	                    );
	        }
	    }


	    // ==========================================
	    // REMOVE DP FROM GEO SEARCH
	    // ==========================================

	    redisTemplate
	            .opsForGeo()
	            .remove(
	                    GEO_KEY,
	                    String.valueOf(deliveryPartnerId)
	            );


	    // ==========================================
	    // DELETE DP LOCATION HASH
	    // ==========================================

	    redisTemplate.delete(currentDeliveryPartnerKey);

	    return order;
		
}

		public Coordinates getRestuarantCoordinates(int orderId) {
			Order orders = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
			return orders.getPickupaddress().getCoordinates();
		}
		
		
		public ResponseStructure<Order> pickupOrder(int orderId) {
			Order orders = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
			
			if(! orders.getStatus().contains("ASSIGNED")) {
				throw new OrderNotFoundException();
			}
			orders.setStatus("ORDER PICKED-UP");
			// Save the updated order
		    Order updatedOrder = orderRepository.save(orders);
			
			ResponseStructure<Order> response = new ResponseStructure<Order>();
			response.setStatuscode(HttpStatus.ACCEPTED.value());
			response.setMessage("ORDER PICKED-UP BY DELIVERY PARTNER");
			response.setData(updatedOrder);
			
			return response;
			
		}

		public Coordinates getCustomerCoordinates(int orderId) {
			  Order orders = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		        
				if(! orders.getStatus().contains("PICK")) {
					throw new OrderNotPickedByDeliveryPartner();
				}
				return orders.getDeliveryorderaddress().getCoordinates();
		
		}

		public ResponseStructure<Order> reachedCustomerLocation(int orderId) {
			 Order orders = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		        
				if(! orders.getStatus().contains("PICK")) {
						throw new OrderNotPickedByDeliveryPartner();
				}
		
				orders.setStatus("LOCATION REACHED");
				// Save the updated order
			    Order updatedOrder = orderRepository.save(orders);
				
				ResponseStructure<Order> response = new ResponseStructure<Order>();
				response.setStatuscode(HttpStatus.OK.value());
				response.setMessage(" LOCATION REACHED");
				response.setData(updatedOrder);
				
						
				return response;
		}

		public void deliveryCompleted(int orderId, int partnerId, double latitude, double longitude) {
			Order orders = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
			if(! orders.getStatus().contains("REACHED")) {
						throw new OrderNotPickedByDeliveryPartner();
			}
			orders.setStatus("ORDER DELIVERED");
			
			
			DeliveryPartner deliveryPartner = orders.getDeliveryPartner();
			deliveryPartner.setOrders(null);
			deliveryPartner.setStatus("AVAILABLE");
			updateLocation(partnerId, latitude, longitude);
			
			ResponseStructure<String> responseStructure = new ResponseStructure<String>();
			responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("ORDER DELIVERED");
			
		}

	

	
			
}

		
		
		
	
	
