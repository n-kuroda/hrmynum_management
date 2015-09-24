package com.athuman.mynumber.web.controller;

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
public class StaffInputCompleteController {
	
	// show staffInputComplete page
	@RequestMapping(value = MyNumberUrl.STAFF_INPUT_COMPLETE, method = RequestMethod.GET)
	public String show(Model model, @RequestParam("token") String requestToken) {
		
		model.addAttribute("token", requestToken);
		return MyNumberJsp.STAFF_INPUT_COMPLETE;
	}

	@RequestMapping(value = MyNumberUrl.STAFF_INPUT_COMPLETE, method = RequestMethod.POST)
	public String complete(Model model,
			@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModel,
			BindingResult binding, 
			WebRequest request, 
			@RequestParam("token") String requestToken) {
		
		// Check token
		if (!ValidateUtil.isValidToken("", request, requestToken, binding, model)) {
			return MyNumberJsp.STAFF_INPUT_COMPLETE;
		}
		
		return MyNumberJsp.REDIRECT_SHAIN_REGIST_CONFIRM;
	}
}
