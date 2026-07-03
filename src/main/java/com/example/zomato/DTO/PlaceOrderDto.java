package com.example.zomato.DTO;

public class PlaceOrderDto {

	private int cid;
	private double packagingFees;
	private double estimatedtime;
	private double deliverycharges;
	private double totaltopay;
	private String comment;
	
	public PlaceOrderDto() {
		super();
	}

	public PlaceOrderDto(int cid, double packagingFees, double estimatedtime, double deliverycharges, double totaltopay,
			String comment) {
		super();
		this.cid = cid;
		this.packagingFees = packagingFees;
		this.estimatedtime = estimatedtime;
		this.deliverycharges = deliverycharges;
		this.totaltopay = totaltopay;
		this.comment = comment;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public double getPackagingFees() {
		return packagingFees;
	}

	public void setPackagingFees(double packagingFees) {
		this.packagingFees = packagingFees;
	}

	public double getEstimatedtime() {
		return estimatedtime;
	}

	public void setEstimatedtime(double estimatedtime) {
		this.estimatedtime = estimatedtime;
	}

	public double getDeliverycharges() {
		return deliverycharges;
	}

	public void setDeliverycharges(double deliverycharges) {
		this.deliverycharges = deliverycharges;
	}

	public double getTotaltopay() {
		return totaltopay;
	}

	public void setTotaltopay(double totaltopay) {
		this.totaltopay = totaltopay;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
