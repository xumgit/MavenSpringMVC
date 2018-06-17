package com.springdemo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springdemo.dao.BlogMapper;
import com.springdemo.pojo.Blog;
import com.springdemo.pojoextend.BlogExtend;
import com.springdemo.pojoextend.BlogViewExtend;

@Service(value="blogService")
public class BlogService {
	
	@Resource
	private BlogMapper blogMapper;
	
	public Blog[] getAllBlog() {
		Blog[] blog = this.blogMapper.selectAll();
		return blog;
	}
	
	public List<Blog> selectBlogByList() {
		List<Blog> blog = this.blogMapper.selectAllByList();
		return blog;
	}
	
	public int deleteBlogById(int id) {
		int affectRow = 0;
		affectRow = this.blogMapper.deleteByPrimaryKey(id);
		return affectRow;
	}
	
	public int insertBlogByList(List<Blog> listBlog) {
		int affectRow = 0;
		affectRow = this.blogMapper.insertBlogByList(listBlog);
		return affectRow;
	}
	
	public BlogExtend selectBlogById(int id) {
		BlogExtend blogExtend = null;
		blogExtend = this.blogMapper.selectBlog(id);
		return blogExtend;
	}
	
	public BlogViewExtend selectBlogAnotherById(int id) {
		BlogViewExtend blogViewExtend = null;
		blogViewExtend = this.blogMapper.selectBlogByAnother(id);
		return blogViewExtend;
	}
	
	public BlogExtend selectBlogThreeById(int id) {
		BlogExtend blogExtend = null;
		blogExtend = this.blogMapper.selectBlogByThree(id);
		return blogExtend;
	}
	
	public BlogExtend selectBlogFourById(int id) {
		BlogExtend blogExtend = null;
		blogExtend = this.blogMapper.selectBlogByThree(id);
		return blogExtend;
	}
	
	public Blog[] selectBlogByDynamicSQL1(Map<String, Object> map) {
		Blog[] blog = this.blogMapper.selectBlogByDynamicSQL1(map);
		return blog;
	}
	
	public Blog[] selectBlogByDynamicSQL2(Map<String, Object> map) {
		Blog[] blog = this.blogMapper.selectBlogByDynamicSQL2(map);
		return blog;
	}
	
	public Blog[] selectBlogByDynamicSQL3(Map<String, Object> map) {
		Blog[] blog = this.blogMapper.selectBlogByDynamicSQL3(map);
		return blog;
	}
	
	public int updateBlogByDynamicSQL4(Map<String, Object> map) {
		int affectRow = this.blogMapper.updateBlogByDynamicSQL4(map);
		return affectRow;
	}
	
	public Blog[] selectBlogByDynamicSQL5(List<Integer> list) {
		Blog[] blog = this.blogMapper.selectBlogByDynamicSQL5(list);
		return blog;
	}
	
}
