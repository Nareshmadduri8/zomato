package com.example.zomato.DTO;

import java.util.List;

import com.example.zomato.Entity.CartItem;
import com.example.zomato.Entity.Restaurant;

public class GetCartDto {

	private Restaurant restaurant;
	private List<CartItem> cartItems;
	double packagingFee;
	double totalItemPrice;
	double estimatedTime;
	double deliveryCharges;
	double distance;
	double totalToPay;
	
	public GetCartDto() {
		super();
	}

	public GetCartDto(Restaurant restaurant, List<CartItem> cartItems, double packagingFee, double totalItemPrice,
			double estimatedTime, double deliveryCharges, double distance, double totalToPay) {
		super();
		this.restaurant = restaurant;
		this.cartItems = cartItems;
		this.packagingFee = packagingFee;
		this.totalItemPrice = totalItemPrice;
		this.estimatedTime = estimatedTime;
		this.deliveryCharges = deliveryCharges;
		this.distance = distance;
		this.totalToPay = totalToPay;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getPackagingFee() {
		return packagingFee;
	}

	public void setPackagingFee(double packagingFee) {
		this.packagingFee = packagingFee;
	}

	public double getTotalItemPrice() {
		return totalItemPrice;
	}

	public void setTotalItemPrice(double totalItemPrice) {
		this.totalItemPrice = totalItemPrice;
	}

	public double getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public double getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(double deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getTotalToPay() {
		return totalToPay;
	}

	public void setTotalToPay(double totalToPay) {
		this.totalToPay = totalToPay;
	}
	
	
}
