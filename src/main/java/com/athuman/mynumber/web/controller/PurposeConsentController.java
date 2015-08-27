package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.athuman.mynumber.web.dto.StaffExistCheckDto;

@Controller
public class PurposeConsentController {


	// inject staffExistCheckDto
	private StaffExistCheckDto staffExistCheckDto;

	@RequestMapping(value = "/purposeConsent", method = RequestMethod.GET)
	public String show(Model model, HttpSession session) {

		model.addAttribute("staffName", session.getAttribute("staffExistCheckDto"));
		return "purposeConsent";
	}

	// submit purposeConsent page
	@RequestMapping(value = "/purposeConsent", method = RequestMethod.POST)
	public String doTransit(@RequestParam String action, Model model) {

		if("back".equals(action)) { // [back] btn clicked

			return "redirect:/staffExistCheck";

		} else { // [consent] btn clicked

			return "redirect:/myNumberRegist";

		}
	}
}
