package com.example.zomato.DTO;

public class DeliveryPartnerDto {

	private String name;
	private long mobno;
	private String gmail;
	private int vehicleNo;
	
	
	public DeliveryPartnerDto() {
		super();
	}


	public DeliveryPartnerDto(String name, long mobno, String gmail, int vehicleNo) {
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


	public int getVehicleNo() {
		return vehicleNo;
	}


	public void setVehicleNo(int vehicleNo) {
		this.vehicleNo = vehicleNo;
	}


	@Override
	public String toString() {
		return "DeliveryPartnerDto [name=" + name + ", mobno=" + mobno + ", gmail=" + gmail + ", vehicleNo=" + vehicleNo
				+ "]";
	}
	
	
	
}
