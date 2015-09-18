package com.athuman.mynumber.web.service;

import org.springframework.http.ResponseEntity;

import com.athuman.mynumber.web.dto.MyNumberResponseDto;
import com.athuman.mynumber.web.dto.ColectionInfoDto;
import com.athuman.mynumber.web.model.MyNumber;

public interface MyNumberAPIService {

	public ResponseEntity<MyNumberResponseDto> myNumber(String himodukeNo);

	public String registMyNumber(MyNumber myNumber);

	public ResponseEntity<MyNumberResponseDto> collectionInfo(ColectionInfoDto colectionInfoDto);
}
