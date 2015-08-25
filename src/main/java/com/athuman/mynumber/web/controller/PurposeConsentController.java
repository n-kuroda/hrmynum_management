package com.athuman.mynumber.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PurposeConsentController {


	// inject shainExistCheckDto
	//private StaffExistCheckDto staffExistCheckDto;

	@RequestMapping(value = "/purposeConsent", method = RequestMethod.GET)
	public String show(Model model) {

		return "purposeConsent";
	}

	// submit shainExistCheck page
	@RequestMapping(value = "/purposeConsent", method = RequestMethod.POST)
	public String search(@RequestParam String action, Model model) {

		// detect the action is [search] or not
		if ("search".equals(action)) {

			return "staffExistCheck";

		} else if("back".equals(action)) { // [back] btn clicked

			return "redirect:/staffExistCheck";

		} else { // [next] btn clicked

			return "redirect:/myNumberRegist";

		}
	}
}
