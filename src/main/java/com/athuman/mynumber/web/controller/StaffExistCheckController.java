package com.athuman.mynumber.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.dto.StaffSelection;

@Controller
public class StaffExistCheckController {

	
	@RequestMapping(value = "/staffExistCheck", method = RequestMethod.GET)
	public String show(Model model) {
		
		model.addAttribute("staffSelection",new StaffSelection());
		return "staffExistCheck";
	}
}
