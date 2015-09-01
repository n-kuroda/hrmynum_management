package com.athuman.mynumber.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.dto.StaffSigningDto;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;

@Controller
public class StaffSigningController {
	
	// show staffSigning page
	@RequestMapping(value = MyNumberUrl.STAFF_SIGNNING, method = RequestMethod.GET)
	public String show(Model model) {
		model.addAttribute("staffSignningDto", new StaffSigningDto());
		return MyNumberJsp.STAFF_SIGNNING;
	}
	
	@RequestMapping(value = MyNumberUrl.STAFF_SIGNNING, method = RequestMethod.POST)
	public String next(StaffSigningDto staffSigningDto, BindingResult binding, Model model) {
		return MyNumberJsp.REGIST_CONFIRM;
	}
	
	@RequestMapping(value = MyNumberUrl.BACK_TO_PARTNER_REGIST, method = RequestMethod.POST)
	public String back(StaffSigningDto staffSigningDto, BindingResult binding, Model model) {
		return MyNumberJsp.REDIRECT_PARTNER_REGIST;
	}
}
