package com.example.zomato.DTO;

public class CustomerDto {

	private String name;
	private long mobno;
	private String gmail;
	private int age;
	private String gender;
	
	public CustomerDto() {
		super();
	}

	public CustomerDto(String name, long mobno, String gmail, int age, String gender) {
		super();
		this.name = name;
		this.mobno = mobno;
		this.gmail = gmail;
		this.age = age;
		this.gender = gender;
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

	@Override
	public String toString() {
		return "CustomerDto [name=" + name + ", mobno=" + mobno + ", gmail=" + gmail + ", age=" + age + ", gender="
				+ gender + "]";
	}
	
	
	
}
