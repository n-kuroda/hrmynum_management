package com.athuman.mynumber.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;

@Controller
public class RegistCompleteController {
	
	// show registComplete page
	@RequestMapping(value = MyNumberUrl.REGIST_COMPLETE, method = RequestMethod.GET)
	public String show(Model model) {
		return MyNumberJsp.REGIST_COMPLETE;
	}

	// submit registComplete page
	@RequestMapping(value = MyNumberUrl.REGIST_COMPLETE, method = RequestMethod.POST)
	public String end(Model model) {
		return MyNumberJsp.REDIRECT_SHAIN_EXIST_CHECK;
	}
}
