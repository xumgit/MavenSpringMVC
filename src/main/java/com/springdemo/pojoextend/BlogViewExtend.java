package com.springdemo.pojoextend;

public class BlogViewExtend {

    private Integer blog_id;

    private Integer blog_author_id;

    private String blog_title;
    
    private String blog_creattime;
    
    private String blog_mainbody;
    
    private String author_name;
    
    private String author_email;
    
    private String comment_creattime;

    private String comment_content;

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

	public String getBlog_creattime() {
		return blog_creattime;
	}

	public void setBlog_creattime(String blog_creattime) {
		this.blog_creattime = blog_creattime;
	}

	public String getBlog_mainbody() {
		return blog_mainbody;
	}

	public void setBlog_mainbody(String blog_mainbody) {
		this.blog_mainbody = blog_mainbody;
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

	public String getComment_creattime() {
		return comment_creattime;
	}

	public void setComment_creattime(String comment_creattime) {
		this.comment_creattime = comment_creattime;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	
}
