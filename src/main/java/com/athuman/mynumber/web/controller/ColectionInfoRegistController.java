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
public class ColectionInfoRegistController {
	
	// show colectionInfoRegist page
	@RequestMapping(value = MyNumberUrl.COLECTION_INFO_REGIST, method = RequestMethod.GET)
	public String show(Model model, HttpSession session) {

		StaffInfoModel staffInfoModel = (StaffInfoModel)session.getAttribute("staffInfoModel");
		if (staffInfoModel == null) {
			staffInfoModel = new StaffInfoModel();
		}
		model.addAttribute("staffInfoModel", staffInfoModel);
		return MyNumberJsp.COLECTION_INFO_REGIST;
	}
	
	// submit colectionInfoRegist page
	@RequestMapping(value = MyNumberUrl.COLECTION_INFO_REGIST, method = RequestMethod.POST)
	public String regist(Model model, HttpSession session) {
		return MyNumberJsp.REDIRECT_REGIST_COMPLETE;
	}
}