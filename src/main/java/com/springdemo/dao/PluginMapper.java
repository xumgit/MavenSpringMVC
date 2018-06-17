package com.springdemo.dao;

import com.springdemo.pojo.Plugin;

public interface PluginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Plugin record);

    int insertSelective(Plugin record);

    Plugin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Plugin record);

    int updateByPrimaryKey(Plugin record);
}