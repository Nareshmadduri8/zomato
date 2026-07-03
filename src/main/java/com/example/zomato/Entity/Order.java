package com.example.zomato.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
	private Customer customer;
	
	@OneToOne
	private Restaurant restaurant;
	
	@OneToOne
	private DeliveryPartner deliveryPartner;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CartItem> items;
	
	@OneToOne
	private Address pickupaddress;
	
	@OneToOne
	private Address deliveryorderaddress;

	public Order() {
		super();
	}

	public Order(double totalamount, String comment, String status, String date, String time,
			double deliverycharges, Customer customer, Restaurant restaurant, DeliveryPartner deliveryPartner, List<CartItem> items,
			Address pickupaddress, Address deliveryorderaddress) {
		this.totalamount = totalamount;
		this.comment = comment;
		this.status = status;
		this.date = date;
		this.time = time;
		this.deliverycharges = deliverycharges;
		this.customer = customer;
		this.restaurant = restaurant;
		this.deliveryPartner = deliveryPartner;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public DeliveryPartner getDeliveryPartner() {
		return deliveryPartner;
	}

	public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
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
