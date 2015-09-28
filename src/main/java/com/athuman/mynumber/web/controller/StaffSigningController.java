package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
public class StaffSigningController {

	// show staffSigning page
	@RequestMapping(value = MyNumberUrl.STAFF_SIGNING, method = RequestMethod.GET)
	public String show(Model model, 
			@RequestParam("token") String requestToken,
			HttpSession session) {
		
		// check session has exist
		if (!ValidateUtil.isNotNullSession(session, model)) {
			return MyNumberJsp.REDIRECT_SHAIN_EXIST_CHECK;
		}
				
		model.addAttribute("token", requestToken);
		return MyNumberJsp.STAFF_SIGNING;
	}

	@RequestMapping(value = MyNumberUrl.STAFF_SIGNING, method = RequestMethod.POST)
	public String next(Model model,
			@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModel,
			BindingResult binding, 
			WebRequest request, 
			@RequestParam("token") String requestToken) {
		
		// Check token
		if (!ValidateUtil.isValidToken("", request, requestToken, binding, model)) {
			return MyNumberJsp.STAFF_SIGNING;
		}
		
		return MyNumberJsp.REDIRECT_STAFF_INPUT_COMPLETE;
	}

	@RequestMapping(value = MyNumberUrl.BACK_TO_PREVIOUS_SCREEN, method = RequestMethod.POST)
	public String back(Model model, 
			HttpSession sesion,
			@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModel,
			BindingResult binding, 
			WebRequest request, 
			@RequestParam("token") String requestToken) {

		// Check token
		if (!ValidateUtil.isValidToken("", request, requestToken, binding, model)) {
			return MyNumberJsp.STAFF_SIGNING;
		}
		
		return MyNumberJsp.REDIRECT_STAFF_REGIST_CONFIRM;
	}
}