package com.athuman.mynumber.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athuman.mynumber.web.dto.MyNumberResponseDto;
import com.athuman.mynumber.web.service.MyNumberAPIService;
import com.athuman.mynumber.web.util.MyNumberUrl;

@Controller
public class MyNumberController {

	@Autowired(required=true)
	@Qualifier(value="myNumberAPIService")
	private MyNumberAPIService myNumberAPIService;
	
	public void setMyNumberAPIService(MyNumberAPIService myNumberAPIService) {
		this.myNumberAPIService = myNumberAPIService;
	}
	
	@RequestMapping(value = MyNumberUrl.MY_NUMBER_API, method = RequestMethod.POST)
	@ResponseBody
	public MyNumberResponseDto callMyNumberAPI(@RequestBody String himodukeNo) throws IOException {

		return myNumberAPIService.myNumber(himodukeNo);
		
	}
}
