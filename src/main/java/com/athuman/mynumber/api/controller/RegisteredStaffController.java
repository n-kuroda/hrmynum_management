package com.athuman.mynumber.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athuman.mynumber.web.dto.TACTRegisteredStaffResponseDto;
import com.athuman.mynumber.web.service.RegisteredStaffAPIService;
import com.athuman.mynumber.web.util.JsonUtil;

@Controller
public class RegisteredStaffController {

	@Autowired(required=true)
	@Qualifier(value="registeredStaffAPIService")
	private RegisteredStaffAPIService registeredStaffAPIService;
	
	public void setRegisteredStaffAPIService(RegisteredStaffAPIService registeredStaffAPIService) {
		this.registeredStaffAPIService = registeredStaffAPIService;
	}

	@RequestMapping(value = "/registeredStaffAPI", method = RequestMethod.GET)
	public String welcome() {
		return "registeredStaffAPI";
	}
	
	@RequestMapping(value = "/registeredStaffAPI", method = RequestMethod.POST)
	@ResponseBody
	public String callMyNumberAPI(@RequestBody String himodukeNo) throws IOException {

		TACTRegisteredStaffResponseDto dto = registeredStaffAPIService.registeredStaff(himodukeNo);
		
		return JsonUtil.toJson(dto);
	}
}
