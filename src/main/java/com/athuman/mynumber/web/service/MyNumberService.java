package com.athuman.mynumber.web.service;

import com.athuman.mynumber.web.dto.ShainInfoDto;
import com.athuman.mynumber.web.dto.StaffInfoDto;

public interface MyNumberService {

	public ShainInfoDto readShain(String shainNo);

	public StaffInfoDto readStaff(String staffNo);

	public void addCollectionInfo();
}
