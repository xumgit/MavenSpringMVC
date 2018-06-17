package com.springdemo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.springdemo.dao.AppPackage;
import com.springdemo.dao.User;
import com.springdemo.dto.UserAppPackageDTO;
import com.springdemo.service.AppPackageService;

import static org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired(required=false)
	private LocaleResolver localeResolver;
	
	@Autowired
	private AppPackageService appPackageService;
	
	@RequestMapping( value = "/dbobj", method = RequestMethod.GET)
	public String databseObject(ModelMap model) {
		List<Object[]> userAppPackageObjects = appPackageService.getUserAppPackageTest();
		
		List<UserAppPackageDTO> userAppPackageDTOs = new ArrayList<UserAppPackageDTO>();
		for (int i = 0; i < userAppPackageObjects.size(); i++) {
			Object[] object = (Object[]) userAppPackageObjects.get(i);
			String name = null;
			if (object[0] != null) {
				name = object[0].toString();
			}
			String platform = null;
			if (object[1] != null) {
				platform = object[1].toString();
			}
			int number = -1;
			if (object[2] != null) {
				number = Integer.valueOf(object[2].toString());
			}
			String firstName = null;
			if (object[3] != null) {
				firstName = object[3].toString();
			}
			int height = -1;
			if (object[4] != null) {
				height = Integer.valueOf(object[4].toString());
			}
			UserAppPackageDTO userAppPackageDTO = new UserAppPackageDTO(name, platform, number, firstName, height);
			userAppPackageDTOs.add(userAppPackageDTO);
		}
		
		model.addAttribute("userAppPackageDTOs", userAppPackageDTOs);
		LOG.info("userAppPackageDTOs=" + userAppPackageDTOs);
		return "databaseDTO";
	}
	
	@RequestMapping( value = "/dbdto", method = RequestMethod.GET)
	public String databseDTO(ModelMap model) {
		List<UserAppPackageDTO> userAppPackageDTOs = appPackageService.getUserAppPackageDTO();
		model.addAttribute("userAppPackageDTOs", userAppPackageDTOs);
		LOG.info("userAppPackageDTOs=" + userAppPackageDTOs);
		return "databaseDTO";
	}
	
	@RequestMapping( value = "/database", method = RequestMethod.GET)
	public String database(ModelMap model) {
		List<AppPackage> appPackages = appPackageService.getAllAppPackage();
		model.addAttribute("appPackages", appPackages);
		LOG.info("appPackages=" + appPackages);
		return "database";
	}
	
	@RequestMapping( value = "/dbtest", method = RequestMethod.GET)
	public String databaseTest(ModelMap model) {
		List<AppPackage> appPackages = appPackageService.getAppPackageOther();
		model.addAttribute("appPackages", appPackages);
		LOG.info("[xum]appPackages=" + appPackages);
		return "databaseTest";
	}
	
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getMovie(@PathVariable String name, ModelMap model) {
        model.addAttribute("name", name);
        model.addAttribute("query", "");
        model.addAttribute("submit", "");
        return "index";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query(@RequestParam("name") String name, ModelMap model) {
        model.addAttribute("name", "");
        model.addAttribute("query", name);
        model.addAttribute("submit", "");
        return "index";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submit(@RequestParam("name") String name, ModelMap model) {
        model.addAttribute("name", "");
        model.addAttribute("query", "");
        model.addAttribute("submit", name);
        return "index";
    }
    
    @RequestMapping(value = "/test")
    public ModelAndView test(@RequestParam("name")String name, 
    		@RequestParam("age")int age) {
    	ModelAndView view = new ModelAndView();
		view.setViewName("test");
		view.addObject("name", name);
		view.addObject("age", age);
		return view;
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ModelAndView testForm(@RequestParam("username")String username, 
    		@RequestParam("password")String password) {
    	ModelAndView view = new ModelAndView();
		view.setViewName("form");
		view.addObject("username", username);
		view.addObject("password", password);
		return view;
    }
    
    @RequestMapping(value = "/getuserform", method = RequestMethod.GET)
    public String getUserForForm(ModelMap model) {
    	User user = new User();
    	user.setId(1);
    	user.setFirstName("adc");
    	user.setSecondName("xyz");
    	user.setAge(50);
    	user.setHeight(170);
    	user.setBirthday((new Date()).toString());
    	model.addAttribute("user", user);
    	return "form/userform";
    }
    
    @RequestMapping(value = "/addform", method = RequestMethod.POST)
    public ModelAndView addForm(@ModelAttribute(value="user")User user, ModelMap model) {
    	ModelAndView view = new ModelAndView();
		view.setViewName("addform");
		view.addObject("user", user);
		view.addObject("firstName", user.getFirstName());
		view.addObject("secondName", user.getSecondName());
		view.addObject("age", user.getAge());
		view.addObject("height", user.getHeight());
		view.addObject("birthday", user.getBirthday());
		return view;
    }
    
    @RequestMapping(value = "/thymeleaf")
    public String thymeleaf(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session=request.getSession();
    	localeResolver.setLocale(request,response,Locale.ENGLISH);
    	List<User> userInfo = getUserInfo();
    	model.addAttribute("userInfo", userInfo);
    	System.out.println("[xum]=" + session.getAttribute(LOCALE_SESSION_ATTRIBUTE_NAME));
    	return "thymeleaf";
    }
    
    private List<User> getUserInfo() {
    	List<User> userInfo = new ArrayList<User>();
    	User user;
    	for (int i=1; i<11; i++) {
    		user = new User();
    		user.setId(i);
    		user.setFirstName("first-" + i);
    		user.setSecondName("second-" + i);
    		user.setAge(i+10);
    		user.setBirthday((new Date()).toString());
    		user.setHeight(i*15);
    		userInfo.add(user);
    	}
    	return userInfo;
    }
}
