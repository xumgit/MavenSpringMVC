package com.springdemo.dao;

import java.util.List;
import java.util.Map;

import com.springdemo.pojo.Blog;
import com.springdemo.pojoextend.BlogExtend;
import com.springdemo.pojoextend.BlogViewExtend;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);
    
    int insertBlogByList(List<Blog> listBlog);

    int insertSelective(Blog record);
    
    List<Blog> selectAllByList();
    
    Blog[] selectAll();
    
    Blog[] selectBlogByDynamicSQL1(Map<String, Object> map);
    
    Blog[] selectBlogByDynamicSQL2(Map<String, Object> map);
    
    Blog[] selectBlogByDynamicSQL3(Map<String, Object> map);
    
    int updateBlogByDynamicSQL4(Map<String, Object> map);
    
    Blog[] selectBlogByDynamicSQL5(List<Integer> list);
    
    BlogExtend selectBlog(Integer id);
    
    BlogViewExtend selectBlogByAnother(Integer id);
    
    BlogExtend selectBlogByThree(Integer id);
    
    BlogExtend selectBlogByFour(Integer id);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);
}