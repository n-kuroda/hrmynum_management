package com.athuman.mynumber.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athuman.mynumber.web.dto.ShainInfoModel;
import com.athuman.mynumber.web.dto.StaffInfoModel;
import com.athuman.mynumber.web.service.MyNumberService;

@Service
public class MyNumberServiceImpl implements MyNumberService {

	@Override
	@Transactional
	public ShainInfoModel readShain(String shainNo) {
		if (shainNo.equals("123456")) {

			ShainInfoModel shainInfoModelDto = new ShainInfoModel();
			shainInfoModelDto.setShainNo(shainNo);
			shainInfoModelDto.setShainNameSei("PHU");
			shainInfoModelDto.setShainNameSeiKana("Phu");
			shainInfoModelDto.setShainNameMei("TRUONG");
			shainInfoModelDto.setShainNameMeiKana("Truong");
			
			return shainInfoModelDto;
		}
		return null;
	}

	@Override
	@Transactional
	public StaffInfoModel readStaff(String staffNo) {
		if (staffNo.equals("154123456")) {

			StaffInfoModel staffInfoModelDto = new StaffInfoModel();
			staffInfoModelDto.setStaffName("Ly Ngan");
			staffInfoModelDto.setStaffNameKana("Nham Ngoc");
			staffInfoModelDto.setStaffNo(staffNo);
			
			return staffInfoModelDto;
		}
		
		return null;
	}

	@Override
	@Transactional
	public void addCollectionInfo() {
		// TODO Auto-generated method stub
	}
}
