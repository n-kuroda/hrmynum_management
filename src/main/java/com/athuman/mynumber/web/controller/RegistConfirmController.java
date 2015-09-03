package com.athuman.mynumber.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.service.MyNumberService;
import com.athuman.mynumber.web.util.MyNumberUrl;

@Controller
public class RegistConfirmController {

	@Autowired(required=true)
	@Qualifier(value="myNumberService")
	private MyNumberService myNumberService;

	public void setMyNumberService(MyNumberService myNumberService) {
		this.myNumberService = myNumberService;
	}

	// show registConfirm page
	@RequestMapping(value = MyNumberUrl.REGISTCONFIRM, method = RequestMethod.GET)
	public String show(Model model) {

		return "registConfirm";
	}

	@RequestMapping(value = MyNumberUrl.REGISTCONFIRM, method = RequestMethod.POST)
	public String next(Model model) {


		return "registConfirm";
	}
}
