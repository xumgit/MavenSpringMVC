package com.springdemo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springdemo.dao.PluginMapper;
import com.springdemo.pojo.Plugin;

@Service("pluginService")
public class PluginService {

	@Resource
	private PluginMapper pluginmapper;
	
	public Plugin getPluginById(int pluginId) {
        return this.pluginmapper.selectByPrimaryKey(pluginId);
    }
}
