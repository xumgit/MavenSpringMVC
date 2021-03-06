package com.springdemo.pojo;

public class Author {
    private Integer id;

    private String name;

    private Integer age;

    private String email;

    private String country;
    
    private String clone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

	public String getClone() {
		return clone;
	}

	public void setClone(String clone) {
		this.clone = clone == null ? null : clone.trim();
	}
}