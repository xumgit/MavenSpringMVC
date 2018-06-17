package com.springdemo.dto;

public class UserAppPackageDTO {
	
	private String name;
	private String platform;
	private int number;
	
	private String firstName;
	private int height;
	
	public UserAppPackageDTO(String name, String platform, int number,
			String firstName, int height){
		this.name = name;
		this.platform = platform;
		this.number = number;
		this.firstName = firstName;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
