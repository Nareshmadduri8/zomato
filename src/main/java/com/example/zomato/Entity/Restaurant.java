package com.example.zomato.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String type;
	@Column(unique = true)
	private long mobno;
	@Column(unique = true)
	private String gmail;
	private double packagingfees;
	private String availability;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@OneToMany
	private List<Item> menu;

	@OneToMany
	private List<Rating> ratings;

	@OneToMany
	private List<Order> orders;

	public Restaurant() {
		super();
	}

	public Restaurant(String name, String type, long mobno, String gmail, double packagingfees, String availability,
			Address address, List<Item> menu, List<Rating> ratings, List<Order> orders) {
		this.name = name;
		this.type = type;
		this.mobno = mobno;
		this.gmail = gmail;
		this.packagingfees = packagingfees;
		this.availability = availability;
		this.address = address;
		this.menu = menu;
		this.ratings = ratings;
		this.orders = orders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getPackagingfees() {
		return packagingfees;
	}

	public void setPackagingfees(double packagingfees) {
		this.packagingfees = packagingfees;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Item> getMenu() {
		return menu;
	}

	public void setMenu(List<Item> menu) {
		this.menu = menu;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
