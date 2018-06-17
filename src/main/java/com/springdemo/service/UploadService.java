package com.springdemo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springdemo.dao.UploadMapper;
import com.springdemo.pojo.Upload;

@Service(value="uploadService")
public class UploadService {

	@Resource
	private UploadMapper uploadMapper;
	
	public int insertUploadSelective(Upload upload) {
		int affectRow = 0;
		affectRow = this.uploadMapper.insertSelective(upload);
		return affectRow;
	}
	
}
