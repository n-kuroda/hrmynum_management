package com.athuman.mynumber.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athuman.mynumber.web.dto.ShainInfoDto;
import com.athuman.mynumber.web.dto.ShainInfoResponseDto;
import com.athuman.mynumber.web.dto.StaffInfoDto;
import com.athuman.mynumber.web.dto.StaffInfoResponseDto;
import com.athuman.mynumber.web.service.MyNumberService;

@Service
public class MyNumberServiceImpl implements MyNumberService {

	@Override
//	@Transactional
	public ShainInfoResponseDto readShain(String shainNo) {

		ShainInfoResponseDto dto = new ShainInfoResponseDto();

		if ("123456".equals(shainNo)) {

			ShainInfoDto shainInfoDto = new ShainInfoDto();
			shainInfoDto.setShainNo(shainNo);
			shainInfoDto.setShainNameSei("PHU");
			shainInfoDto.setShainNameSeiKana("Phu");
			shainInfoDto.setShainNameMei("TRUONG");
			shainInfoDto.setShainNameMeiKana("Truong");

			dto.setHttpStatus(200);
			dto.setResultMessage("OK");
			dto.setShainInfoDto(shainInfoDto);

		} else if ("123457".equals(shainNo)) { // 204

			dto.setHttpStatus(204);
			dto.setResultMessage("Error 204");
			dto.setShainInfoDto(new ShainInfoDto());

		} else {
			dto.setHttpStatus(400);
			dto.setResultMessage("Error 400");
			dto.setShainInfoDto(new ShainInfoDto());
		}

		return dto;
	}

	@Override
//	@Transactional
	public StaffInfoResponseDto readStaff(String staffNo) {

		StaffInfoResponseDto dto = new StaffInfoResponseDto();

		if (staffNo.equals("154123456")) {

			StaffInfoDto staffInfoDto = new StaffInfoDto();
			staffInfoDto.setStaffName("Ly Ngan");
			staffInfoDto.setStaffNameKana("Nham Ngoc");
			staffInfoDto.setStaffNo(staffNo);

			dto.setHttpStatus(200);
			dto.setResultMessage("OK");
			dto.setStaffInfoDto(staffInfoDto);

		} else if ("154123457".equals(staffNo)) { // 204

			dto.setHttpStatus(204);
			dto.setResultMessage("Error 204");
			dto.setStaffInfoDto(new StaffInfoDto());

		} else {
			dto.setHttpStatus(400);
			dto.setResultMessage("Error 400");
			dto.setStaffInfoDto(new StaffInfoDto());
		}

		return dto;
	}

	@Override
	@Transactional
	public void addCollectionInfo() {
		// TODO Auto-generated method stub
	}
}
