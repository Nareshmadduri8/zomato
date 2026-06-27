package com.example.zomato.Entity;

import java.util.List;

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
	
	@OneToMany
	private List<Address> address;
	
	@OneToMany 
	private List<Order> orders;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CartItem> cart;
	
	
	public Customer() {
		super();
	}

	public Customer(String name, int age, String gender, long mobno, String gmail, double wallet,
			List<Address> address, List<Order> orders, List<CartItem> cart) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mobno = mobno;
		this.gmail = gmail;
		this.wallet = wallet;
		this.address = address;
		this.orders = orders;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<CartItem> getCart() {
		return cart;
	}

	public void setCart(List<CartItem> cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", mobno=" + mobno
				+ ", gmail=" + gmail + ", wallet=" + wallet + ", address=" + address + ", orders=" + orders + ", cart="
				+ cart + "]";
	}
	
	
	
	
}
