package com.springdemo.dao;

import com.springdemo.pojo.Upload;
import com.springdemo.pojo.UploadWithBLOBs;

public interface UploadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UploadWithBLOBs record);

    int insertSelective(UploadWithBLOBs record);

    UploadWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UploadWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UploadWithBLOBs record);

    int updateByPrimaryKey(Upload record);
}