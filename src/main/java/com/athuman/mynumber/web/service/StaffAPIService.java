package com.athuman.mynumber.web.service;

import com.athuman.mynumber.web.dto.StaffInfoResponseDto;

public interface StaffAPIService {
	
	public StaffInfoResponseDto readStaff(String staffNo);
}
