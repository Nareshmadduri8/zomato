package com.example.zomato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.zomato.DTO.DeliveryPartnerDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.Entity.Coordinates;
import com.example.zomato.Entity.DeliveryPartner;
import com.example.zomato.Entity.Order;
import com.example.zomato.Service.DeliveryPartnerService;

import jakarta.validation.Valid;

@RestController
public class DeliveryPartnerController {

	
	@Autowired
	private DeliveryPartnerService deliveryPartnerService;
	
	@PostMapping("/deliverypartner/savedp")
	public void savedeliverypartner(@Valid @RequestBody DeliveryPartnerDto dpdto) {
		deliveryPartnerService.savedeliverypartner(dpdto);
	}
	
	@GetMapping("/deliverypartner/finddp")
	public ResponseStructure<DeliveryPartner> finddeliverypartner(@RequestParam int id) {
		 return deliveryPartnerService.finddeliverypartner(id);
	}
	
	
	@DeleteMapping("/deliverypartner/deletedp")
	public ResponseStructure<DeliveryPartner> deletedeliverypartner(@RequestParam int id) {
		return deliveryPartnerService.deletedeliverypartner(id);
	}
	
	
	@PostMapping("/deliverypartner/sendCurrentCoordinates")
	public String sendCurrentCoordinates(@RequestParam int deliveryPartnerId, @RequestParam double latitude, double longitude){
		deliveryPartnerService.updateLocation(deliveryPartnerId,latitude,longitude);
	    return "Location Updated successfully";
	}
	
	
	@GetMapping("/deliverypartner/assignedorders")
	  public List<Order> getAssignedOrders(@RequestParam Long deliverypartnerId) {
	    return deliveryPartnerService.getAssignedOrders(deliverypartnerId);
	  }
	
	@PostMapping("/deliverypartner/acceptorder")
	public Order acceptorderbydeliverypartner(@RequestParam int deliveryPartnerId, @RequestParam int orderid) {
		return deliveryPartnerService.acceptOrderByDeliveryPartner(deliveryPartnerId,orderid);
	}
	
	@GetMapping("/deliverypartner/getrestuarantcoordinates")
	public Coordinates getRestuarantCoordinates(@RequestParam int orderId) {
		return deliveryPartnerService.getRestuarantCoordinates(orderId);
	}
	
	@GetMapping("deliverypartner/getrestuarantmaps")
	public RedirectView getRestuarantMaps( @RequestParam int orderId) {

	    Coordinates coordinates = deliveryPartnerService.getRestuarantCoordinates(orderId);

	    String mapsUrl =
	            "https://www.google.com/maps/dir/?api=1"
	            + "&destination="
	            + coordinates.getLatitude()
	            + ","
	            + coordinates.getLongitude();

	    return new RedirectView(mapsUrl);
	}
	
	@GetMapping("/deliverypartner/pickuporder")
	public ResponseStructure<Order> pickupOrder(int orderId) {
		return deliveryPartnerService.pickupOrder(orderId);
	}
	
	@GetMapping("/deliverypartner/getcustomercoordinates")
	public Coordinates getCustomerCoordinates(@RequestParam int orderId) {
		return deliveryPartnerService.getCustomerCoordinates(orderId);
	}
	
	@GetMapping("deliverypartner/getcustomermaps")
	public RedirectView getCustomerMaps(
	        @RequestParam int orderId) {

	    Coordinates coordinates =
	            deliveryPartnerService
	                    .getCustomerCoordinates(orderId);

	    String mapsUrl =
	            "https://www.google.com/maps/dir/?api=1"
	            + "&destination="
	            + coordinates.getLatitude()
	            + ","
	            + coordinates.getLongitude();

	    return new RedirectView(mapsUrl);
	}
	
	@GetMapping("deliverypartner/reachedcustomer")
	public ResponseStructure<Order> reacedCustomerLocation(@RequestParam int orderId) {
		return deliveryPartnerService.reachedCustomerLocation(orderId);
	}
	
	@PostMapping("deliverypartner/deliverycompleted")
	public void deliveryCompleted(int orderId, int partnerId, double latitude, double longitude) {
		deliveryPartnerService.deliveryCompleted(orderId, partnerId, latitude, longitude);
	}
}






















