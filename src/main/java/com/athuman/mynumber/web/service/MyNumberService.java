package com.athuman.mynumber.web.service;

import com.athuman.mynumber.web.dto.ShainInfoResponseDto;
import com.athuman.mynumber.web.dto.StaffInfoResponseDto;
import com.athuman.mynumber.web.dto.TACTMyNumberResponseDto;
import com.athuman.mynumber.web.dto.TACTRegistConfirmDto;
import com.athuman.mynumber.web.model.MyNumber;

public interface MyNumberService {

	public ShainInfoResponseDto readShain(String shainNo);

	public StaffInfoResponseDto readStaff(String staffNo);

	public void addCollectionInfo();

	public String registMyNumber (MyNumber myNumber);

	public TACTMyNumberResponseDto registrationInformationCollected(TACTRegistConfirmDto tactRegistration);
}
