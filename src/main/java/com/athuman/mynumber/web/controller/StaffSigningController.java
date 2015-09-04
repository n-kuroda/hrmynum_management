package com.athuman.mynumber.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.dto.StaffSigningDto;
import com.athuman.mynumber.web.util.MyNumberJsp;

@Controller
public class StaffSigningController {

	// show staffSigning page
	@RequestMapping(value = "/staffSignning", method = RequestMethod.GET)
	public String show(Model model) {
		model.addAttribute("staffSignningDto", new StaffSigningDto());
		return "staffSignning";
	}

	@RequestMapping(value = "/staffSignning", method = RequestMethod.POST)
	public String next(StaffSigningDto staffSigningDto, BindingResult binding, Model model) {
		return MyNumberJsp.REDIRECT_REGIST_COMFIRM;
	}

	@RequestMapping(value = "/backPartnerRegist", method = RequestMethod.POST)
	public String back(StaffSigningDto staffSigningDto, BindingResult binding, Model model) {
		return "redirect:/partnerRegist";
	}
}
