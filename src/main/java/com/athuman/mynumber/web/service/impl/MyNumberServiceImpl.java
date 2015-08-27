package com.athuman.mynumber.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athuman.mynumber.web.dto.ShainExistCheckDto;
import com.athuman.mynumber.web.dto.StaffExistCheckDto;
import com.athuman.mynumber.web.service.MyNumberService;

@Service
public class MyNumberServiceImpl implements MyNumberService {

	@Override
	@Transactional
	public ShainExistCheckDto readShain(String shainNo) {
		if (shainNo.equals("123456")) {

			ShainExistCheckDto shainExistCheckDto = new ShainExistCheckDto();
			shainExistCheckDto.setFirstName("Phu");
			shainExistCheckDto.setLastName("Truong");
			shainExistCheckDto.setFirstNameKana("PHU");
			shainExistCheckDto.setLastNameKana("TRUONG");
			shainExistCheckDto.setEmployeeId(shainNo);
			
			return shainExistCheckDto;
		}
		return null;
	}

	@Override
	@Transactional
	public StaffExistCheckDto readStaff(String staffNo) {
		if (staffNo.equals("154123456")) {

			StaffExistCheckDto staffExistCheckDto = new StaffExistCheckDto();
			staffExistCheckDto.setStaffName("Ly Ngan");
			staffExistCheckDto.setStaffNameKana("Nham Ngoc");
			staffExistCheckDto.setStaffNo(staffNo);
			
			return staffExistCheckDto;
		}
		
		return null;
	}

	@Override
	@Transactional
	public void addCollectionInfo() {
		// TODO Auto-generated method stub
	}
}
