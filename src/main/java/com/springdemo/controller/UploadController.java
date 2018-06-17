package com.springdemo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.springdemo.pojo.Upload;
import com.springdemo.pojoextend.UploadExtend;
import com.springdemo.service.UploadService;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

	private static final Logger LOG = LogManager.getLogger(UploadController.class);
	
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping(value="/path", method = RequestMethod.GET)
	public String path(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		//String path = request.getServletContext().getRealPath("/images/");
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String servletPath = request.getServletPath();
		String contextPath = request.getContextPath();
		String requestURL = request.getRequestURI();
		String webRoot = System.getProperty("web.root");
		
		model.addAttribute("realPath", realPath);
		model.addAttribute("servletPath", servletPath);
		model.addAttribute("contextPath", contextPath);
		model.addAttribute("requestURL", requestURL);
		model.addAttribute("webRoot", webRoot);
		
		return "upload/path";
	}
	
	@RequestMapping(value="/upload")
	public String upload(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		/*if ("GET".equalsIgnoreCase(request.getMethod())){
			LOG.info("get");
		}else{
			LOG.info("post");
		}
		model.addAttribute("uploadExtend", uploadExtend);*/
		LOG.info("UPLOAD");
		return "upload/upload";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public void add(ModelMap model, UploadExtend uploadExtend, HttpServletRequest request, HttpServletResponse response) {
		int affectRow = -1;
		String status = "{\"Reason\":\"none\",\"saveToDatabase\":\"" + affectRow + "\",\"saveToUpload\":\"failure\"}";
		String webRoot = System.getProperty("web.root");
        String uploadPath = webRoot + "static" + File.separator + "upload";
		try {
			File file = new File(uploadPath);
			if (!file.exists()) {
				LOG.info("mkdir file="+uploadPath);
				file.mkdirs();
			}
			MultipartFile uploadFile = uploadExtend.getFileUpload();
			boolean isEmpty = uploadFile.isEmpty();
			
			if (!isEmpty) {
				//String name = uploadFile.getName();	
				String originalFileName = uploadFile.getOriginalFilename();
				String contentType = uploadFile.getContentType();			
				byte[] by = uploadFile.getBytes();				
				if (by.length > 1024*1024*10) {
					status = "{\"Reason\":\"exceed\",\"saveToDatabase\":\"" + affectRow + "\",\"saveToUpload\":\"failure\"}";
				} else {
					long size = uploadFile.getSize();
					InputStream is = uploadFile.getInputStream();
					Upload upload = new Upload();
					byte[] filedata = new byte[(int)size];  
					is.read(filedata);
					upload.setIsempty(String.valueOf(isEmpty));
					upload.setContenttype(contentType);
					upload.setSize((int)size);
					upload.setFiledata(filedata);
					upload.setFilename(originalFileName);
					upload.setProperty(uploadExtend.getProperty());
					affectRow = uploadService.insertUploadSelective(upload);
					status = "{\"Reason\":\"none\",\"saveToDatabase\":\"" + affectRow + "\",\"saveToUpload\":\"failure\"}";
					File tempFile = new File(uploadPath + File.separator + originalFileName);  
					if (tempFile.exists()) {  
						boolean delResult = tempFile.delete();  
						LOG.info("delResult="+delResult); 
					}
					
					if (!originalFileName.equalsIgnoreCase("")) {
						File serverFile = new File(uploadPath + File.separator + originalFileName);
						uploadFile.transferTo(serverFile);
						status = "{\"Reason\":\"none\",\"saveToDatabase\":\"" + affectRow + "\",\"saveToUpload\":\"success\"}";
						/*FileOutputStream fos = new FileOutputStream(uploadPath + originalFileName);
						byte[] buffer = new byte[8192];
						int count = 0;
						while ((count = is.read(buffer)) > 0) {
							fos.write(buffer, 0, count);
						}
						fos.close();
						is.close();*/
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			LOG.error(ioe.getMessage(), ioe);
		} catch (Exception e) {
	    	e.printStackTrace();
			LOG.info(e.getMessage(), e);
		}
		
		response.setContentType("text/html;UTF-8");
		try (PrintWriter writer = response.getWriter();) {
			writer.write(status);
			writer.flush();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		
		//model.addAttribute("affectRow", affectRow);
		//return "upload/add";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public void save(@RequestParam("fileUpload")MultipartFile[] fileUpload, @RequestParam("property")String property,
			HttpServletRequest request, HttpServletResponse response) {	
		int failureSaveDataBaseCount = 0;
		int successSaveToUploadCount = 0;
		int totalUploadFileSize = 0;
		int upoadFileSize = fileUpload.length;
		JsonObject data = new JsonObject();
		JsonArray arrayFailure = new JsonArray();
		JsonObject jsonObjFailure = new JsonObject();
				
		String webRoot = System.getProperty("web.root");
        String uploadPath = webRoot + "static" + File.separator + "upload";
		try {
			for (int j = 0; j < upoadFileSize; j++) {
				MultipartFile uploadFile = fileUpload[j];
				boolean isEmpty = uploadFile.isEmpty();

				if (!isEmpty) {
					// String name = uploadFile.getName();
					totalUploadFileSize += 1;
					String originalFileName = uploadFile.getOriginalFilename();
					String contentType = uploadFile.getContentType();
					long size = uploadFile.getSize();
					InputStream is = uploadFile.getInputStream();
					Upload upload = new Upload();
					byte[] filedata = new byte[(int) size];
					is.read(filedata);
					upload.setIsempty(String.valueOf(isEmpty));
					upload.setContenttype(contentType);
					upload.setSize((int) size);
					upload.setFiledata(filedata);
					upload.setFilename(originalFileName);
					upload.setProperty(property);
					int affectRow = uploadService.insertUploadSelective(upload);
					if (affectRow <= 0) {
						failureSaveDataBaseCount += 1;
						jsonObjFailure.addProperty("filename", originalFileName);
						jsonObjFailure.addProperty("reason", "database");
						arrayFailure.add(jsonObjFailure);
					}
					File tempFile = new File(uploadPath + File.separator + originalFileName);
					if (tempFile.exists()) {
						boolean delResult = tempFile.delete();
						LOG.info("delResult=" + delResult);
					}
					if (!originalFileName.equalsIgnoreCase("")) {
						File serverFile = new File(uploadPath + File.separator + originalFileName);
						uploadFile.transferTo(serverFile);
						successSaveToUploadCount += 1;
					}
				}		
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			LOG.error(ioe.getMessage(), ioe);
		} catch (Exception e) {
	    	e.printStackTrace();
			LOG.info(e.getMessage(), e);
		}
		
		data.addProperty("total", totalUploadFileSize);
		data.addProperty("failureSaveDataBaseCount", failureSaveDataBaseCount);
		data.addProperty("successSaveToUploadCount", successSaveToUploadCount);
		data.add("failure", arrayFailure);
		LOG.info("data="+data.toString());
		response.setContentType("text/html;UTF-8");
		try (PrintWriter writer = response.getWriter();) {
			writer.write(data.toString());
			writer.flush();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	@RequestMapping(value="/down")
	public ResponseEntity<byte[]> downLoadByResponseEntity(@RequestParam("filename") String filename, HttpServletRequest request) {
		//String path = request.getServletContext().getRealPath("/upload/");
		String path = request.getServletContext().getRealPath("/static/");
		LOG.info("path="+path);
		LOG.info("filename="+filename);
		//filename = "D:\\eula\\test.txt";
		//String downloadFileName = "";
		byte[] body = null;
		try {
			//filename = new String(filename.getBytes("UTF-8"), "UTF-8");
			File file = new File(path + File.separator + "images" + File.separator + filename);
			//File file = new File(filename);
			LOG.info("file exists="+file.exists());
			if (file.exists()) {
				InputStream inputStream = new FileInputStream(file);
				body = new byte[inputStream.available()];
				inputStream.read(body);
				inputStream.close();
			}
			filename = new String(filename.getBytes("utf-8"),"iso-8859-1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//headers.setContentDispositionFormData("attachment", filename);
		headers.add("Content-Disposition", "attchement;filename=" + filename);
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
		return entity;
	}
	
}
