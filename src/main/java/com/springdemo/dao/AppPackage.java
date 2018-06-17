package com.springdemo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apppackage")
public class AppPackage {
	
	@Id
	@GeneratedValue
	@Column(name = "id", length = 5)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "platform", length = 45)
	private String platform;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "number", length = 5)
	private int number;
	
	@Column(name = "size", length = 45)
	private String size;
	
	@Column(name = "lastEdit", length = 45)
	private String lastEdit;

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

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getLastEdit() {
		return lastEdit;
	}

	public void setLastEdit(String lastEdit) {
		this.lastEdit = lastEdit;
	}
	
	@Override  
    public String toString() {  
        return "AppPackage ["
        		+ "\n id=" + this.id 
        		+ "\n name=" + this.name 
        		+ "\n platform=" + this.platform 
        		+ "\n value" + this.value 
        		+ "\n number=" + this.number 
        		+ "\n size=" + this.size 
        		+ "\n lastEdit=" + this.lastEdit 
        		+ "]";  
    }
}
