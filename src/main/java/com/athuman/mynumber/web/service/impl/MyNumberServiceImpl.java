package com.athuman.mynumber.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athuman.mynumber.web.dto.ShainInfoDto;
import com.athuman.mynumber.web.dto.StaffInfoDto;
import com.athuman.mynumber.web.service.MyNumberService;

@Service
public class MyNumberServiceImpl implements MyNumberService {

	@Override
	@Transactional
	public ShainInfoDto readShain(String shainNo) {
		if (shainNo.equals("123456")) {

			ShainInfoDto shainInfoDto = new ShainInfoDto();
			shainInfoDto.setShainNo(shainNo);
			shainInfoDto.setShainNameSei("PHU");
			shainInfoDto.setShainNameSeiKana("Phu");
			shainInfoDto.setShainNameMei("TRUONG");
			shainInfoDto.setShainNameMeiKana("Truong");

			return shainInfoDto;
		}
		return null;
	}

	@Override
	@Transactional
	public StaffInfoDto readStaff(String staffNo) {
		if (staffNo.equals("154123456")) {

			StaffInfoDto staffInfoDto = new StaffInfoDto();
			staffInfoDto.setStaffName("Ly Ngan");
			staffInfoDto.setStaffNameKana("Nham Ngoc");
			staffInfoDto.setStaffNo(staffNo);

			return staffInfoDto;
		}

		return null;
	}

	@Override
	@Transactional
	public void addCollectionInfo() {
		// TODO Auto-generated method stub
	}
}
