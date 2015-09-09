package com.athuman.mynumber.web.service.impl;

import com.athuman.mynumber.web.dto.ShainInfoDto;
import com.athuman.mynumber.web.dto.ShainInfoResponseDto;
import com.athuman.mynumber.web.service.ShainAPIService;

public class ShainAPIServiceImpl implements ShainAPIService {

	@Override
	public ShainInfoResponseDto readShain(String shainNo) {
		ShainInfoResponseDto dto = new ShainInfoResponseDto();

		if ("123456".equals(shainNo)) {

			ShainInfoDto shainInfoDto = new ShainInfoDto();
			shainInfoDto.setShainNo(shainNo);
			shainInfoDto.setShainNameSei("Phu");
			shainInfoDto.setShainNameSeiKana("PHU");
			shainInfoDto.setShainNameMei("Truong");
			shainInfoDto.setShainNameMeiKana("TRUONG");

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

}
