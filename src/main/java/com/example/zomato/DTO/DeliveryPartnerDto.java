package com.example.zomato.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class DeliveryPartnerDto {

//	private String name;
//	private long mobno;
//	private String gmail;
//	private int vehicleNo;
	
	  @NotBlank(message = "Name is required")
	    private String name;

	  @Min(value = 6000000000L, message = "Invalid mobile number")
	  @Max(value = 9999999999L, message = "Invalid mobile number")
	  private long mobno;
	  

	    @NotBlank(message = "Email is required")
	    @Email(message = "Enter a valid email address")
	    private String gmail;

	    @Pattern(
	        regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$",
	        message = "Enter a valid vehicle number (e.g., TS09AB1234)"
	    )
	    private String vehicleNo;
	
	
	public DeliveryPartnerDto() {
		super();
	}


	public DeliveryPartnerDto(String name, long mobno, String gmail, String vehicleNo) {
		super();
		this.name = name;
		this.mobno = mobno;
		this.gmail = gmail;
		this.vehicleNo = vehicleNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getMobno() {
		return mobno;
	}


	public void setMobno(long mobno) {
		this.mobno = mobno;
	}


	public String getGmail() {
		return gmail;
	}


	public void setGmail(String gmail) {
		this.gmail = gmail;
	}


	public String getVehicleNo() {
		return vehicleNo;
	}


	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}


	@Override
	public String toString() {
		return "DeliveryPartnerDto [name=" + name + ", mobno=" + mobno + ", gmail=" + gmail + ", vehicleNo=" + vehicleNo
				+ "]";
	}
	
	
	
}
