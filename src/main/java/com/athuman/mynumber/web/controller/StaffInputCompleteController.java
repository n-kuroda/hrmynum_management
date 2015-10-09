package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
public class StaffInputCompleteController {
	
	// show staffInputComplete page
	@RequestMapping(value = MyNumberUrl.STAFF_INPUT_COMPLETE, method = RequestMethod.GET)
	public String show(Model model,
			HttpSession session) {
		
		// check session has exist
		if (!ValidateUtil.isNotNullSession(session, model)) {
			return MyNumberJsp.REDIRECT_SHAIN_EXIST_CHECK;
		}
				
		return MyNumberJsp.STAFF_INPUT_COMPLETE;
	}

	@RequestMapping(value = MyNumberUrl.STAFF_INPUT_COMPLETE, method = RequestMethod.POST)
	public String complete(Model model,
			@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModel,
			BindingResult binding, 
			WebRequest request) {
		return MyNumberJsp.REDIRECT_SHAIN_REGIST_CONFIRM;
	}
}
