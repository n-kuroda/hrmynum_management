package com.athuman.mynumber.web.service;

import com.athuman.mynumber.web.dto.ShainExistCheckDto;
import com.athuman.mynumber.web.dto.StaffExistCheckDto;

public interface MyNumberService {

	public ShainExistCheckDto readShain(String shainNo);
	
	public StaffExistCheckDto readStaff(String staffNo);
	
	public void addCollectionInfo();
}
