package com.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@RequestMapping("/print")
	public String print(ModelMap model) {
		model.addAttribute("message", "hello xum");
		return "hello";
	}
	
}
