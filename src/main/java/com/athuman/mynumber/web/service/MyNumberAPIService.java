package com.athuman.mynumber.web.service;

import com.athuman.mynumber.web.dto.MyNumberResponseDto;
import com.athuman.mynumber.web.dto.RegistConfirmDto;
import com.athuman.mynumber.web.model.MyNumber;

public interface MyNumberAPIService {

	public MyNumberResponseDto myNumber(String himodukeNo);
	
	public String registMyNumber(MyNumber myNumber);
	
	public MyNumberResponseDto collectionInfo(RegistConfirmDto registConfirmDto);
}
