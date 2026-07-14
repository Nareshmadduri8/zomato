package com.example.zomato.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CustomerDto {

//	private String name;
//	private long mobno;
//	private String gmail;
//	private int age;
//	private String gender;
	
	 @NotBlank(message = "Name is required")
	    private String name;

	 @Min(value = 6000000000L, message = "Invalid mobile number")
	 @Max(value = 9999999999L, message = "Invalid mobile number")
	 private long mobno;

	    @NotBlank(message = "Email is required")
	    @Email(message = "Invalid email address")
	    private String gmail;

	    @Min(value = 18, message = "Age must be at least 18")
	    @Max(value = 100, message = "Age must not exceed 100")
	    private int age;

	    @NotBlank(message = "Gender is required")
	    @Pattern(
	        regexp = "^(?i)(male|female|other)$",
	        message = "Gender must be Male, Female, or Other"
	    )
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
