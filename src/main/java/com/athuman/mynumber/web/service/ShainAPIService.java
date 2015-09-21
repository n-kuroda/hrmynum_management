package com.athuman.mynumber.web.service;

import org.springframework.http.ResponseEntity;

import com.athuman.mynumber.web.dto.ShainInfoResponseDto;

public interface ShainAPIService {

	public ResponseEntity<ShainInfoResponseDto> readShain(String shainNo);
}
