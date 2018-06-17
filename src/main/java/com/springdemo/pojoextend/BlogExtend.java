package com.springdemo.pojoextend;

import com.springdemo.pojo.Author;
import com.springdemo.pojo.Comment;

public class BlogExtend {
	
    private Integer id;

    private Integer authorid;

    private String title;

    private String creattime;

    private String mainbody;
    
    private Author author;
    
    private Comment comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuthorid() {
		return authorid;
	}

	public void setAuthorid(Integer authorid) {
		this.authorid = authorid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreattime() {
		return creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}

	public String getMainbody() {
		return mainbody;
	}

	public void setMainbody(String mainbody) {
		this.mainbody = mainbody;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
