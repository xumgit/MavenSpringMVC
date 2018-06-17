package com.springdemo.pojoextend;

import org.springframework.web.multipart.MultipartFile;

import com.springdemo.pojo.Upload;

public class UploadExtend extends Upload {
	
	private MultipartFile fileUpload;

	public MultipartFile getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(MultipartFile fileUpload) {
		this.fileUpload = fileUpload;
	}
	
}
