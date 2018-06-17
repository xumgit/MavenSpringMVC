package com.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdemo.dao.AppPackage;
import com.springdemo.daomanager.AppPackageManager;
import com.springdemo.dto.UserAppPackageDTO;

@Service
public class AppPackageService  {
	
	@Autowired
	private AppPackageManager appPackageManager;
  
    public AppPackage getAppPackage(int id) {
        return appPackageManager.getAppPackage(id);
    }
    
    public List<Object[]> getUserAppPackageTest() {
        return appPackageManager.getManyTableTest();
    }
    
    public List<UserAppPackageDTO> getUserAppPackageDTO() {
        return appPackageManager.getManyTable();
    }
    
    public List<AppPackage> getAppPackageOther() {
        return appPackageManager.getTwo();
    }

    public List<AppPackage> getAllAppPackage() {
        return appPackageManager.getAllAppPackage();
    }

    @Transactional
    public void addUser(AppPackage appPackage) {
    	appPackageManager.addAppPackage(appPackage);
    }

    @Transactional
    public boolean delAppPackage(int id) {
        return appPackageManager.delAppPackage(id);
    }

    @Transactional
    public boolean updateUser(AppPackage appPackage) {
        return appPackageManager.updateAppPackage(appPackage);
    }


}
