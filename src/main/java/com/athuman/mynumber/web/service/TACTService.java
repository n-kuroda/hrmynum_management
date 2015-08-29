package com.athuman.mynumber.web.service;

import com.athuman.mynumber.web.dto.TACTMyNumberResponseDto;
import com.athuman.mynumber.web.dto.TACTRegisteredStaffResponseDto;

public interface TACTService {
	
	public TACTRegisteredStaffResponseDto registeredStaff(String himodukeNo);
	
	public TACTMyNumberResponseDto myNumber(String himodukeNo);

}
