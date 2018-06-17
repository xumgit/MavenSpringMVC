package com.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
		
	@RequestMapping("/login")
    public String login(Model model, @RequestParam("username")String username, 
    		@RequestParam("password")String password) {
		model.addAttribute("username", username);
		model.addAttribute("password", password);
        return "login";
    }
	
	@RequestMapping("/checkout")
	public String checkout(ModelMap model, @RequestParam("username")String username, 
    		@RequestParam("password")String password) {
		model.addAttribute("username", username);
		model.addAttribute("password", password);
        return "login";
	}
	
	@RequestMapping("/test")
	public ModelAndView testModel(@RequestParam("username")String username, 
    		@RequestParam("password")String password) {
		ModelAndView view = new ModelAndView("login");
		/*
		 * ModelAndView view = new ModelAndView();
			view.setViewName("login");
		 * */
		view.addObject("username",username);
		view.addObject("password",password);
		return view;
	}
	
    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }
	
/*	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("username") String userName,
			@RequestParam("password") String password) {
		ModelAndView mv = new ModelAndView("menu");
		mv.addObject("name", userName);
		return mv;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() {
		ModelAndView mv = new ModelAndView("logout");
		mv.addObject("message", "logout");
		return mv;
	}*/
}
