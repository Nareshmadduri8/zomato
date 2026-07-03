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

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int age;
	private String gender;
	@Column(unique = true)
	private long mobno;
	@Column(unique = true)
	private String gmail;
	private double wallet;
	private double totalcartvalue;
	private double currentcartdeliverycharges;
	
	@OneToMany
	private List<Address> address;
	
	@JsonIgnore
	@OneToMany 
	private List<Order> Activeorders;
	
	@JsonIgnore
	@OneToMany 
	private List<Order> Completedorders;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CartItem> cart;
	
	
	public Customer() {
		super();
	}

	public Customer(int id, String name, int age, String gender, long mobno, String gmail, double wallet,
			double totalcartvalue, double currentcartdeliverycharges, List<Address> address, List<Order> activeorders,
			List<Order> completedorders, List<CartItem> cart) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mobno = mobno;
		this.gmail = gmail;
		this.wallet = wallet;
		this.totalcartvalue = totalcartvalue;
		this.currentcartdeliverycharges = currentcartdeliverycharges;
		this.address = address;
		Activeorders = activeorders;
		Completedorders = completedorders;
		this.cart = cart;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
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

	public List<CartItem> getCart() {
		return cart;
	}

	public void setCart(List<CartItem> cart) {
		this.cart = cart;
	}
	
	public double getTotalcartvalue() {
		return totalcartvalue;
	}

	public void setTotalcartvalue(double totalcartvalue) {
		this.totalcartvalue = totalcartvalue;
	}

	public double getCurrentcartdeliverycharges() {
		return currentcartdeliverycharges;
	}

	public void setCurrentcartdeliverycharges(double currentcartdeliverycharges) {
		this.currentcartdeliverycharges = currentcartdeliverycharges;
	}

//	@Override
//	public String toString() {
//		return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", mobno=" + mobno
//				+ ", gmail=" + gmail + ", wallet=" + wallet + ", address=" + address + ", Activeorders=" + Activeorders
//				+ ", Completedorders=" + Completedorders + ", cart=" + cart + "]";
//	}

	
	
	
	
}
