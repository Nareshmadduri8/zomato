package com.example.zomato.DTO;

public class ItemDto {

	private String name;
	private String type;
	private double price;
	private int quantity;
	private String description;
	private String category;
	private String availability;
	
	public ItemDto() {
		super();
	}

	public ItemDto(String name, String type, double price, int quantity, String description, String category,
			String availability) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.category = category;
		this.availability = availability;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "ItemDto [name=" + name + ", type=" + type + ", price=" + price + ", quantity=" + quantity
				+ ", description=" + description + ", category=" + category + ", availability=" + availability + "]";
	}
	
	
}
