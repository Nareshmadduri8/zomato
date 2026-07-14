package com.example.zomato.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RestaurantDto {

//	private String name;
//	private String type;
//	private long mobno;
//	private String gmail;
//	private double latitude;
//	private double longitude;

	
	 @NotBlank(message = "Restaurant name is required")
	    private String name;

	    @NotBlank(message = "Restaurant type is required")
	    @Pattern(
	        regexp = "^(?i)(veg|non-veg|both)$",
	        message = "Type must be Veg, Non-Veg, or Both"
	    )
	    private String type;
	    
	    @Min(value = 6000000000L, message = "Invalid mobile number")
	    @Max(value = 9999999999L, message = "Invalid mobile number")
	    private long mobno;
	    

	    @NotBlank(message = "Email is required")
	    @Email
	    (message = "Enter a valid email address")
	    private String gmail;

	    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
	    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
	    private double latitude;

	    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
	    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
	    private double longitude;
	
	public RestaurantDto() {
		super();
	}

	public RestaurantDto(String name, String type, long mobno, String gmail, double latitude, double longitude) {
		super();
		this.name = name;
		this.type = type;
		this.mobno = mobno;
		this.gmail = gmail;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "RestaurantDto [name=" + name + ", type=" + type + ", mobno=" + mobno + ", gmail=" + gmail + "]";
	}
	
	
	
}
