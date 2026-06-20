package com.example.zomato.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double totalamount;
	private String comment;
	private String status;
	private String date;
	private String time;
	private double deliverycharges;
	
	@OneToOne
	private Customer c;
	
	@OneToOne
	private Restaurant r;
	
	@OneToOne
	private DeliveryPartner dp;
	
	@OneToMany
	private List<Item> items;
	
	@OneToOne
	private Address pickupaddress;
	
	@OneToOne
	private Address deliveryorderaddress;

	public Order() {
		super();
	}

	public Order(double totalamount, String comment, String status, String date, String time,
			double deliverycharges, Customer c, Restaurant r, DeliveryPartner dp, List<Item> items,
			Address pickupaddress, Address deliveryorderaddress) {
		this.totalamount = totalamount;
		this.comment = comment;
		this.status = status;
		this.date = date;
		this.time = time;
		this.deliverycharges = deliverycharges;
		this.c = c;
		this.r = r;
		this.dp = dp;
		this.items = items;
		this.pickupaddress = pickupaddress;
		this.deliveryorderaddress = deliveryorderaddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getDeliverycharges() {
		return deliverycharges;
	}

	public void setDeliverycharges(double deliverycharges) {
		this.deliverycharges = deliverycharges;
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}

	public Restaurant getR() {
		return r;
	}

	public void setR(Restaurant r) {
		this.r = r;
	}

	public DeliveryPartner getDp() {
		return dp;
	}

	public void setDp(DeliveryPartner dp) {
		this.dp = dp;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Address getPickupaddress() {
		return pickupaddress;
	}

	public void setPickupaddress(Address pickupaddress) {
		this.pickupaddress = pickupaddress;
	}

	public Address getDeliveryorderaddress() {
		return deliveryorderaddress;
	}

	public void setDeliveryorderaddress(Address deliveryorderaddress) {
		this.deliveryorderaddress = deliveryorderaddress;
	}
	
	
	
	
	
}
