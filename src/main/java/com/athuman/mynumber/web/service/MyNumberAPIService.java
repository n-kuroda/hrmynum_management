package com.athuman.mynumber.web.service;

import com.athuman.mynumber.web.dto.TACTMyNumberResponseDto;
import com.athuman.mynumber.web.dto.TACTRegistConfirmDto;
import com.athuman.mynumber.web.model.MyNumber;

public interface MyNumberAPIService {

	public TACTMyNumberResponseDto myNumber(String himodukeNo);
	
	public String registMyNumber (MyNumber myNumber);
	
	public TACTMyNumberResponseDto collectionInfo(TACTRegistConfirmDto tACTRegistConfirmDto);
}
