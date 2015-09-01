package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;

@Controller
public class PurposeConsentController {

	@RequestMapping(value = MyNumberUrl.PURPOSE_CONSENT, method = RequestMethod.GET)
	public String show(Model model) {
		return MyNumberJsp.PURPOSE_CONSENT;
	}

	// submit purposeConsent page
	@RequestMapping(value = MyNumberUrl.PURPOSE_CONSENT, method = RequestMethod.POST)
	public String doTransitConsent(Model model) {
		return MyNumberJsp.REDIRECT_MYNUMBER_REGIST;
	}

	@RequestMapping(value = MyNumberUrl.BACK_TO_STAFF_EXIST_CHECK, method = RequestMethod.POST)
	public String doTransitBack(Model model, HttpSession sesion) {
		return MyNumberJsp.REDIRECT_STAFF_EXIST_CHECK;
	}
}
