package com.athuman.mynumber.web.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.athuman.mynumber.web.dto.StaffInfoDto;
import com.athuman.mynumber.web.dto.StaffInfoResponseDto;
import com.athuman.mynumber.web.service.StaffAPIService;

public class StaffAPIServiceImpl implements StaffAPIService {

	// TODO: replace hard-code in returned value below in case [readStaff API] is created.
	@Override
	public ResponseEntity<StaffInfoResponseDto> readStaff(String staffNo) {
		
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		StaffInfoResponseDto dto = new StaffInfoResponseDto();

		if (staffNo.equals("123456789")) {

			StaffInfoDto staffInfoDto = new StaffInfoDto();
			staffInfoDto.setNameSei("田中");
			staffInfoDto.setNameKanaSei("タナカ");
			staffInfoDto.setNameMei("太郎");
			staffInfoDto.setNameKanaMei("タロウ");

			status = HttpStatus.OK;			
			dto.setResultMessage("OK");
			dto.setStaffInfoDto(staffInfoDto);

		} else if ("154123457".equals(staffNo)) { // 204

			status = HttpStatus.NO_CONTENT;			
			dto.setResultMessage("Error 204");
			dto.setStaffInfoDto(new StaffInfoDto());

		} else {
			
			status = HttpStatus.BAD_REQUEST;			
			dto.setResultMessage("Error 400");
			dto.setStaffInfoDto(new StaffInfoDto());
		}

		return new ResponseEntity<StaffInfoResponseDto>(dto, headers , status);
	}

}
