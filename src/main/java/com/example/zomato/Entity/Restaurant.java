package com.example.zomato.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	@OneToMany
	private List<Order> Activeorders;
	
	@JsonIgnore
	@OneToMany
	private List<Order> Completedorders;


	public Restaurant() {
		super();
	}

	public Restaurant(String name, String type, long mobno, String gmail, double packagingfees, String availability,
			Address address, List<Item> menu, List<Rating> ratings, List<Order> Activeorders,List<Order> Compeletedorders) {
		this.name = name;
		this.type = type;
		this.mobno = mobno;
		this.gmail = gmail;
		this.packagingfees = packagingfees;
		this.availability = availability;
		this.address = address;
		this.menu = menu;
		this.ratings = ratings;
		this.Activeorders = Activeorders;
		this.Completedorders=Compeletedorders;
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

	public List<Order> getActiveorders() {
		return Activeorders;
	}

	public void setActiveorders(List<Order> activeorders) {
		Activeorders = activeorders;
	}

	public List<Order> getCompletedorders() {
		return Completedorders;
	}

	public void setCompletedorders(List<Order> completedorders) {
		Completedorders = completedorders;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", type=" + type + ", mobno=" + mobno + ", gmail=" + gmail
				+ ", packagingfees=" + packagingfees + ", availability=" + availability + ", address=" + address
				+ ", menu=" + menu + ", ratings=" + ratings + ", Activeorders=" + Activeorders + ", Completedorders="
				+ Completedorders + "]";
	}

	
	
}
