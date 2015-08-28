package com.athuman.mynumber.web.service;

import com.athuman.mynumber.web.dto.ShainInfoModel;
import com.athuman.mynumber.web.dto.StaffInfoModel;

public interface MyNumberService {

	public ShainInfoModel readShain(String shainNo);
	
	public StaffInfoModel readStaff(String staffNo);
	
	public void addCollectionInfo();
}
