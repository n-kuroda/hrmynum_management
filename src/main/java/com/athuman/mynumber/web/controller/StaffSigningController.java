package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;

@Controller
public class StaffSigningController {

	// show staffSigning page
	@RequestMapping(value = MyNumberUrl.STAFF_SIGNNING, method = RequestMethod.GET)
	public String show(Model model) {
		return MyNumberJsp.STAFF_SIGNNING;
	}

	@RequestMapping(value = MyNumberUrl.STAFF_SIGNNING, method = RequestMethod.POST)
	public String next(Model model) {
		return MyNumberJsp.REDIRECT_REGIST_COMFIRM;
	}

	@RequestMapping(value = MyNumberUrl.BACK_TO_PREVIOUS_SCREEN, method = RequestMethod.POST)
	public String back(Model model, HttpSession sesion) {
		StaffInfoModel staffInfoModelSession = (StaffInfoModel)sesion.getAttribute("staffInfoModel");
		if ("0".equals(staffInfoModelSession.getConsent())) {
			return MyNumberJsp.REDIRECT_PURPOSE_CONSENT;
		}
		
		return MyNumberJsp.REDIRECT_PARTNER_REGIST;
	}
}
