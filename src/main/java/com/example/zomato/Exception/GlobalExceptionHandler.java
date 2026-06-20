package com.example.zomato.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.zomato.DTO.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerAlearyExistsExecption.class)
	public  ResponseStructure<String> HandleCustomerAlearyExistsExecption() {	
		ResponseStructure<String>rs=new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.ALREADY_REPORTED.value());
		rs.setMessage("CUSTOMER ALEARDY EXISTS");
		rs.setData("CUSTOMER ALEARDY PRESENT INT THE DATABASE");
		return rs;
		}
	
	
	//Customer not found Exception
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseStructure<String> customerNotFoundExecptionHandler() {
		ResponseStructure<String> rs= new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("customer with the mobno not found !!!");
		rs.setData( null);
		return rs;}
		
		
		//======================================================================
		@ExceptionHandler(RestaurantNotFoundException.class)
		public ResponseStructure<String> ResturantNotFoundExecptionHandler() {
			ResponseStructure<String> rs= new ResponseStructure<String>();
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Resturants with the id not found !!!");
			rs.setData( null);
			return rs;
	}

		@ExceptionHandler(DeliveryPartnerNotFoundExce.class)
		public ResponseStructure<String> deliveryPartnerNotFoundExecptionHandler() {
			ResponseStructure<String> rs= new ResponseStructure<String>();
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("deliveryPartner with the mobno not found !!!");
			rs.setData( null);
			return rs;
		}
		
		//Response not found Execption
				@ExceptionHandler(ResponseNotFoundforCoardinatesException.class)
				public ResponseStructure<String> ResponseNotFoundforCoardinatesExceptionHandler(){
					ResponseStructure<String> rs=new ResponseStructure<String>();
					rs.setStatuscode(HttpStatus.NOT_FOUND.value());
					rs.setMessage("COORDINATES ARE NOT CORRECT ! !!!");
					rs.setData( null);
					return rs;
					
				}

}
