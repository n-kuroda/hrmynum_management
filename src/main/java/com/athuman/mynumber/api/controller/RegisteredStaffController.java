package com.athuman.mynumber.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athuman.mynumber.web.dto.RegisteredStaffAPIResponseDto;
import com.athuman.mynumber.web.service.RegisteredStaffAPIService;
import com.athuman.mynumber.web.util.ConstValues;
import com.athuman.mynumber.web.util.MyNumberUrl;

@Controller
@RequestMapping(value = MyNumberUrl.REGISTERED_STAFF_API)
public class RegisteredStaffController {

	@Autowired(required=true)
	@Qualifier(value="registeredStaffAPIService")
	private RegisteredStaffAPIService registeredStaffAPIService;
	
	public void setRegisteredStaffAPIService(RegisteredStaffAPIService registeredStaffAPIService) {
		this.registeredStaffAPIService = registeredStaffAPIService;
	}
	
	@RequestMapping(value = MyNumberUrl.HIMODUKE_NO, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<RegisteredStaffAPIResponseDto> search(@PathVariable String himodukeNo) throws IOException {

		ResponseEntity<RegisteredStaffAPIResponseDto> result = null;
		
		try {
			result = registeredStaffAPIService.registeredStaff(himodukeNo);
		} catch (Exception ex) { 
			
			RegisteredStaffAPIResponseDto dto = new RegisteredStaffAPIResponseDto();

			HttpHeaders headers = new HttpHeaders();
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			dto.setResultMessage(ConstValues.API_MSG_UNEXPECTED_ERROR);
			dto.setResult(ConstValues.API_RESULT_0);

			result = new ResponseEntity<RegisteredStaffAPIResponseDto>(dto, headers, status);

		}
		return result;
	}
}
