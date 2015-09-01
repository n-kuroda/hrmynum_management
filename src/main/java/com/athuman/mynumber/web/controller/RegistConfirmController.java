package com.athuman.mynumber.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistConfirmController {

	// show staffSigning page
		@RequestMapping(value = "/registConfirm", method = RequestMethod.GET)
		public String show(Model model) {

//			model.addAttribute("staffSignningDto", new StaffSigningDto());
			return "registConfirm";
		}
}
