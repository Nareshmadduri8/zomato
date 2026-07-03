package com.example.zomato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.zomato.DTO.DeliveryPartnerDto;
import com.example.zomato.DTO.LocationDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.Entity.DeliveryPartner;
import com.example.zomato.Service.DeliveryPartnerLocationService;
import com.example.zomato.Service.DeliveryPartnerService;

@RestController
public class DeliveryPartnerController {

	
	@Autowired
	private DeliveryPartnerService deliverypartnerservice;
	
	@Autowired
	private DeliveryPartnerLocationService deliveryPartnerLocationService;
	
	@PostMapping("/deliverypartner/savedp")
	public void savedeliverypartner(@RequestBody DeliveryPartnerDto dpdto) {
		deliverypartnerservice.savedeliverypartner(dpdto);
	}
	
	@GetMapping("/deliverypartner/finddp")
	public ResponseStructure<DeliveryPartner> finddeliverypartner(@RequestParam int id) {
		 return deliverypartnerservice.finddeliverypartner(id);
	}
	
	
	@DeleteMapping("/deliverypartner/deletedp")
	public ResponseStructure<DeliveryPartner> deletedeliverypartner(@RequestParam int id) {
		return deliverypartnerservice.deletedeliverypartner(id);
	}
	
	@PostMapping("/sendCurrentCoordinates")
	public String sendCurrentCoordinates(@RequestBody LocationDto locationdto){
		deliveryPartnerLocationService.updateLocation(locationdto);
	    return "Location Updated successfully";
	}
}






















