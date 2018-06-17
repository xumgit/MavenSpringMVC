package com.springdemo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springdemo.pojo.Blog;
import com.springdemo.pojoextend.BlogExtend;
import com.springdemo.pojoextend.BlogViewExtend;
import com.springdemo.service.BlogService;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {
	
	private static final Logger LOG = LogManager.getLogger(BlogController.class);
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value="/curd")
	public String blodCURD() {		
		return "blog/curd";
	}
	
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public String blogGetAll(HttpServletRequest request, ModelMap model) {
		int id1 = Integer.parseInt(request.getParameter("id1"));
		int id2 = Integer.parseInt(request.getParameter("id2"));
		LOG.info("id1="+id1+",id2="+id2);
		Blog[] blogs = blogService.getAllBlog();
		LOG.info("blogs="+blogs.length);
		model.addAttribute("blogs", blogs);
		return "blog/index";
	}
	
	@RequestMapping(value="/pagehelper", method = RequestMethod.GET)
	public String blogPageHelper(HttpServletRequest request, @RequestParam(required=true,defaultValue="1") Integer page,
            @RequestParam(required=false,defaultValue="4") Integer pageSize, ModelMap model) {
		int page_ = -1;
		int pageSize_ = -1;
		if (request.getParameter("page") != null) {
			page_ = Integer.parseInt(request.getParameter("page"));
		}
		if (request.getParameter("pageSize") != null) {
			pageSize_ = Integer.parseInt(request.getParameter("pageSize"));
		}		
		LOG.info("page_="+page_+",pageSize_="+pageSize_);
		
		LOG.info("page="+page+",pageSize="+pageSize);
		int offset = 0;
		if (page > 0) {
			offset = (page - 1) * pageSize;
		}		
		Map<String, Object> mapPara = new HashMap<String, Object>();
		mapPara.put("offset", offset);
		mapPara.put("pageSize", pageSize);
			
		PageHelper.startPage(page, pageSize);
		List<Blog> blogList = blogService.selectBlogByList();
		LOG.info("blogs="+blogList.size());
		PageInfo<Blog> p = new PageInfo<Blog>(blogList);
		model.addAttribute("blogList", blogList);
		model.addAttribute("page", p);
		return "blog/pagehelper";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public void blogDelete(HttpServletRequest request, HttpServletResponse response) {
		String status = "{\"status\":\"failure\"}";
		int id = Integer.parseInt(request.getParameter("id"));
		LOG.info("delete id="+id);
		int affectRow = blogService.deleteBlogById(id);
		if (affectRow != 0) {
			status = "{\"status\":\"success\"}";
		}
		
		response.setContentType("text/html;UTF-8");
		try (PrintWriter writer = response.getWriter();) {
			writer.write(status);
			writer.flush();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public void insertBlog(HttpServletRequest request, HttpServletResponse response) {
		String status = "{\"status\":\"failure\"}";
		int authorid = Integer.parseInt(request.getParameter("authorid"));
		String title = String.valueOf(request.getParameter("title"));
		String mainbody = String.valueOf(request.getParameter("mainbody"));
		    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String creattime = df.format(now);
		
		List<Blog> listBlog = new ArrayList<Blog>();
		Blog blog = new Blog();
		blog.setAuthorid(authorid);
		blog.setTitle(title);
		blog.setMainbody(mainbody);
		blog.setCreattime(creattime);
		listBlog.add(blog);
		
		int affectRow = blogService.insertBlogByList(listBlog);
		if (affectRow != 0) {
			status = "{\"status\":\"success\"}";
		}
		
		response.setContentType("text/html;UTF-8");
		try (PrintWriter writer = response.getWriter();) {
			writer.write(status);
			writer.flush();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	@RequestMapping(value="/selectblog", method = RequestMethod.GET)
	public String selectBlog(ModelMap model) {
		BlogExtend blogExtend = blogService.selectBlogById(3);
		LOG.info("blog="+blogExtend.toString());
		model.addAttribute("blogExtend", blogExtend);
		return "blog/select";
	}
	
	@RequestMapping(value="/selectbloganother", method = RequestMethod.GET)
	public String selectBlogAnother(ModelMap model) {
		BlogViewExtend blogViewExtend = blogService.selectBlogAnotherById(3);
		model.addAttribute("blogViewExtend", blogViewExtend);
		return "blog/selectanother";
	}
	
	@RequestMapping(value="/selectblogthree", method = RequestMethod.GET)
	public String selectBlogThree(ModelMap model) {
		BlogExtend blogExtendView = blogService.selectBlogThreeById(3);
		model.addAttribute("blogExtendViewThree", blogExtendView);
		return "blog/selectthree";
	}
	
	@RequestMapping(value="/selectblogfour", method = RequestMethod.GET)
	public String selectBlogFour(ModelMap model) {
		LOG.info("mybatis cache");
		BlogExtend blogExtendView = blogService.selectBlogFourById(3);
		model.addAttribute("blogExtendViewFour", blogExtendView);
		return "blog/selectfour";
	}
	
	@RequestMapping(value="/dynamicsql", method = RequestMethod.GET)
	public String selectBlogByDynamicSQL(HttpServletRequest request, ModelMap model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 3);
		map.put("authorid", 1);
		map.put("title", "sky");
		map.put("mainbody", "sky");
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", 3);	
		map1.put("title", "sky");
		map1.put("mainbody", "the sky is perfect blue modify.");
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(1);
		list.add(3);
		Blog[] blogByDynamicSQL1 = blogService.selectBlogByDynamicSQL1(map);
		Blog[] blogByDynamicSQL2 = blogService.selectBlogByDynamicSQL2(map);
		Blog[] blogByDynamicSQL3 = blogService.selectBlogByDynamicSQL3(map);
		int affectRow = blogService.updateBlogByDynamicSQL4(map1);
		Blog[] blogByDynamicSQL5 = blogService.selectBlogByDynamicSQL5(list);
		model.addAttribute("blogByDynamicSQL1", blogByDynamicSQL1);
		model.addAttribute("blogByDynamicSQL2", blogByDynamicSQL2);
		model.addAttribute("blogByDynamicSQL3", blogByDynamicSQL3);
		LOG.info("affectRow="+affectRow);
		model.addAttribute("blogByDynamicSQL5", blogByDynamicSQL5);
		return "blog/dynamicsql";
	}
	
}
