package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.model.DependentsInfoListModel;
import com.athuman.mynumber.web.model.StaffInfoModel;
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
	public String next(Model model, HttpSession sesion) {

		StaffInfoModel staffInfoModelSession = (StaffInfoModel)sesion.getAttribute("staffInfoModel");
		staffInfoModelSession.setConsent("1");
		sesion.setAttribute("staffInfoModel", staffInfoModelSession);

		return MyNumberJsp.REDIRECT_MYNUMBER_REGIST;
	}

	@RequestMapping(value = MyNumberUrl.BACK_TO_STAFF_EXIST_CHECK, method = RequestMethod.POST)
	public String back(Model model) {
		return MyNumberJsp.REDIRECT_STAFF_EXIST_CHECK;
	}

	@RequestMapping(value = MyNumberUrl.SKIP_TO_SIGNNING_SCREEN, method = RequestMethod.POST)
	public String skipToSignningScreen(Model model, HttpSession sesion) {

		StaffInfoModel staffInfoModelSession = (StaffInfoModel)sesion.getAttribute("staffInfoModel");
		// clear staff info.
		staffInfoModelSession.setMyNumber("");
		staffInfoModelSession.setMyNumberConfirm("");
		staffInfoModelSession.setDriversLicense("");
		staffInfoModelSession.setDriveHistoryLicense("");
		staffInfoModelSession.setPassPort("");
		staffInfoModelSession.setBodyDisabilitiesNotebook("");
		staffInfoModelSession.setMentalDisabilitiesNotebook("");
		staffInfoModelSession.setRehabilitationNotebook("");
		staffInfoModelSession.setStayCard("");
		staffInfoModelSession.setClearPerson("");
		staffInfoModelSession.setHealthInsuranceLicense("");
		staffInfoModelSession.setPensionNotebook("");
		staffInfoModelSession.setOther("");
		staffInfoModelSession.setConsent("0");

		DependentsInfoListModel dependentsSession = (DependentsInfoListModel)sesion.getAttribute("dependentsInfoListModel");
		dependentsSession = new DependentsInfoListModel();

		sesion.setAttribute("staffInfoModel", staffInfoModelSession);
		sesion.setAttribute("dependentsInfoListModel", dependentsSession);

		return MyNumberJsp.REDIRECT_STAFF_SIGNNING;
	}
}
