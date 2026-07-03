package com.example.zomato.DTO;

public class RestaurantDto {

	private String name;
	private String type;
	private long mobno;
	private String gmail;
	private double latitude;
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
