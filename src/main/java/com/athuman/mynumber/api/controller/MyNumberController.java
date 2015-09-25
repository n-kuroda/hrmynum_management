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

import com.athuman.mynumber.web.dto.MyNumberResponseDto;
import com.athuman.mynumber.web.service.MyNumberAPIService;
import com.athuman.mynumber.web.util.ConstValues;
import com.athuman.mynumber.web.util.MyNumberUrl;

@Controller
@RequestMapping(value = MyNumberUrl.MY_NUMBER_API)
public class MyNumberController {

	@Autowired(required=true)
	@Qualifier(value="myNumberAPIService")
	private MyNumberAPIService myNumberAPIService;
	
	public void setMyNumberAPIService(MyNumberAPIService myNumberAPIService) {
		this.myNumberAPIService = myNumberAPIService;
	}
	
	@RequestMapping(value = MyNumberUrl.HIMODUKE_NO, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MyNumberResponseDto> search(@PathVariable String himodukeNo) throws IOException {

		ResponseEntity<MyNumberResponseDto> result = null;
		
		try {
			result = myNumberAPIService.myNumber(himodukeNo);
		} catch (Exception ex) { 
			
			MyNumberResponseDto dto = new MyNumberResponseDto();

			HttpHeaders headers = new HttpHeaders();
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			dto.setResultMessage(ConstValues.API_OTHER_ERROR);
			dto.setMyNumber("");

			result = new ResponseEntity<MyNumberResponseDto>(dto, headers, status);

		}
		return result;
		
	}
}
