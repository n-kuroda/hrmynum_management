package com.athuman.mynumber.web.service.impl;

import com.athuman.mynumber.web.dto.StaffInfoDto;
import com.athuman.mynumber.web.dto.StaffInfoResponseDto;
import com.athuman.mynumber.web.service.StaffAPIService;

public class StaffAPIServiceImpl implements StaffAPIService {

	@Override
	public StaffInfoResponseDto readStaff(String staffNo) {
		StaffInfoResponseDto dto = new StaffInfoResponseDto();

		if (staffNo.equals("154123456")) {

			StaffInfoDto staffInfoDto = new StaffInfoDto();
			staffInfoDto.setNameSei("Ngan");
			staffInfoDto.setNameKanaSei("NGAN");
			staffInfoDto.setNameMei("Ly");
			staffInfoDto.setNameKanaMei("LY");

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

}
