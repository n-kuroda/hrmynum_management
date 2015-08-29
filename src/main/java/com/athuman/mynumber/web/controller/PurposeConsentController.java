package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PurposeConsentController {

	@RequestMapping(value = "/purposeConsent", method = RequestMethod.GET)
	public String show(Model model) {
		return "purposeConsent";
	}

	// submit purposeConsent page
	@RequestMapping(value = "/purposeConsent", method = RequestMethod.POST)
	public String doTransitConsent(Model model) {
		return "redirect:/myNumberRegist";
	}

	@RequestMapping(value = "/purposeConsentBack", method = RequestMethod.POST)
	public String doTransitBack(Model model, HttpSession sesion) {
		
		sesion.invalidate();
		return "redirect:/staffExistCheck";
	}
}
