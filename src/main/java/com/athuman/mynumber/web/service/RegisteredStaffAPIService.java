package com.athuman.mynumber.web.service;

import org.springframework.http.ResponseEntity;

import com.athuman.mynumber.web.dto.RegisteredStaffAPIResponseDto;

public interface RegisteredStaffAPIService {

	public ResponseEntity<RegisteredStaffAPIResponseDto> registeredStaff(String himodukeNo);

}
