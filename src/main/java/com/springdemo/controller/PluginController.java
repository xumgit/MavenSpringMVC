package com.springdemo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springdemo.pojo.Plugin;
import com.springdemo.service.PluginService;

@Controller
@RequestMapping("/plugin")
public class PluginController {
	
	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private PluginService pluginService;
	
	@RequestMapping( value = "/index", method = RequestMethod.GET)
	public String pluginIndex(HttpServletRequest request, ModelMap model) {
		// int pluginId = Integer.parseInt(request.getParameter("id"));
		Plugin plugin = pluginService.getPluginById(1);
		model.addAttribute("plugins", plugin);
		LOG.info("plugin=" + plugin);
		return "pluginIndex";
	}

}
