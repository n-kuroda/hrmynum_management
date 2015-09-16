package com.athuman.mynumber.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;

@Controller
public class StaffInputCompleteController {
	
	// show staffInputComplete page
	@RequestMapping(value = MyNumberUrl.STAFF_INPUT_COMPLETE, method = RequestMethod.GET)
	public String show(Model model) {
		return MyNumberJsp.STAFF_INPUT_COMPLETE;
	}

	@RequestMapping(value = MyNumberUrl.STAFF_INPUT_COMPLETE, method = RequestMethod.POST)
	public String complete(Model model) {
		return MyNumberJsp.REDIRECT_SHAIN_REGIST_CONFIRM;
	}
}
