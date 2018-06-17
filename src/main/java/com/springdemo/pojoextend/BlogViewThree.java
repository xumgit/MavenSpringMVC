package com.springdemo.pojoextend;

public class BlogViewThree {

	private Integer author_id;

    private String author_name;
    
    private String author_email;
    
    private Integer blog_id;

    private Integer blog_author_id;

    private String blog_title;

	public Integer getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getAuthor_email() {
		return author_email;
	}

	public void setAuthor_email(String author_email) {
		this.author_email = author_email;
	}

	public Integer getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Integer blog_id) {
		this.blog_id = blog_id;
	}

	public Integer getBlog_author_id() {
		return blog_author_id;
	}

	public void setBlog_author_id(Integer blog_author_id) {
		this.blog_author_id = blog_author_id;
	}

	public String getBlog_title() {
		return blog_title;
	}

	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}
	
}
