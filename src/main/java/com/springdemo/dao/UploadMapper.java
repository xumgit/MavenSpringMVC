package com.springdemo.dao;

import com.springdemo.pojo.Upload;

public interface UploadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Upload record);

    int insertSelective(Upload record);

    Upload selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Upload record);

    int updateByPrimaryKey(Upload record);
}