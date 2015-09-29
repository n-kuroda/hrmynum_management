package com.athuman.mynumber.web.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.athuman.mynumber.web.dto.ShainInfoDto;
import com.athuman.mynumber.web.dto.ShainInfoResponseDto;
import com.athuman.mynumber.web.service.ShainAPIService;

public class ShainAPIServiceImpl implements ShainAPIService {

	// TODO: replace hard-code in returned value below in case [readShain API] is created.
	@Override
	public ResponseEntity<ShainInfoResponseDto> readShain(String shainNo) {
		
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		
		ShainInfoResponseDto dto = new ShainInfoResponseDto();

		if ("123456".equals(shainNo)) {

			ShainInfoDto shainInfoDto = new ShainInfoDto();
			shainInfoDto.setShainNo(shainNo);
			shainInfoDto.setShainNameSei("鈴木");
			shainInfoDto.setShainNameSeiKana("スズキ");
			shainInfoDto.setShainNameMei("一郎");
			shainInfoDto.setShainNameMeiKana("イチロウ");

			status = HttpStatus.OK;
			
			dto.setResultMessage("OK");
			dto.setShainInfoDto(shainInfoDto);

		} else if ("123457".equals(shainNo)) { // 204

			status = HttpStatus.NO_CONTENT;
			
			dto.setResultMessage("Error 204");
			dto.setShainInfoDto(new ShainInfoDto());

		} else {
			status = HttpStatus.BAD_REQUEST;
			
			dto.setResultMessage("Error 400");
			dto.setShainInfoDto(new ShainInfoDto());
		}

		return new ResponseEntity<ShainInfoResponseDto>(dto, headers , status);
	}

}
