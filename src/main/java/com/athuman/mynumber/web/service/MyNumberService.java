package com.athuman.mynumber.web.service;

import com.athuman.mynumber.web.dto.ShainInfoResponseDto;
import com.athuman.mynumber.web.dto.StaffInfoResponseDto;

public interface MyNumberService {

	public ShainInfoResponseDto readShain(String shainNo);

	public StaffInfoResponseDto readStaff(String staffNo);

	public void addCollectionInfo();
}
