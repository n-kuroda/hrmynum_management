package com.athuman.mynumber.web.service;

import org.springframework.http.ResponseEntity;

import com.athuman.mynumber.web.dto.StaffInfoResponseDto;

public interface StaffAPIService {
	
	public ResponseEntity<StaffInfoResponseDto> readStaff(String staffNo);
}
