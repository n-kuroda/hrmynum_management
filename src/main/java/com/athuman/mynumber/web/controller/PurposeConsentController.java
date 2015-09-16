package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.model.DependentsInfoListModel;
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.util.ConstValues;
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
	public String consent(Model model, HttpSession sesion) {

		StaffInfoModel staffInfoModelSession = (StaffInfoModel)sesion.getAttribute("staffInfoModel");
		staffInfoModelSession.setConsent(ConstValues.CONSENT_VALUE_1);
		sesion.setAttribute("staffInfoModel", staffInfoModelSession);

		return MyNumberJsp.REDIRECT_MYNUMBER_REGIST;
	}

	@RequestMapping(value = MyNumberUrl.BACK_TO_STAFF_EXIST_CHECK, method = RequestMethod.POST)
	public String back(Model model) {
		return MyNumberJsp.REDIRECT_STAFF_EXIST_CHECK;
	}

	@RequestMapping(value = MyNumberUrl.SKIP_TO_STAFF_REGIST_CONFIRM_SCREEN, method = RequestMethod.POST)
	public String other(Model model, HttpSession sesion) {

		StaffInfoModel staffInfoModelSession = (StaffInfoModel)sesion.getAttribute("staffInfoModel");
		// clear staff info.
		staffInfoModelSession.setMyNumber(ConstValues.BLANK);
		staffInfoModelSession.setMyNumberConfirm(ConstValues.BLANK);
		staffInfoModelSession.setDriversLicense(ConstValues.BLANK);
		staffInfoModelSession.setDriveHistoryLicense(ConstValues.BLANK);
		staffInfoModelSession.setPassPort(ConstValues.BLANK);
		staffInfoModelSession.setBodyDisabilitiesNotebook(ConstValues.BLANK);
		staffInfoModelSession.setMentalDisabilitiesNotebook(ConstValues.BLANK);
		staffInfoModelSession.setRehabilitationNotebook(ConstValues.BLANK);
		staffInfoModelSession.setStayCard(ConstValues.BLANK);
		staffInfoModelSession.setHealthInsuranceLicense(ConstValues.BLANK);
		staffInfoModelSession.setPensionNotebook(ConstValues.BLANK);
		staffInfoModelSession.setOther(ConstValues.BLANK);

		staffInfoModelSession.setConsent(ConstValues.CONSENT_VALUE_0);

		DependentsInfoListModel dependentsSession = (DependentsInfoListModel)sesion.getAttribute("dependentsInfoListModel");
		dependentsSession = new DependentsInfoListModel();

		sesion.setAttribute("staffInfoModel", staffInfoModelSession);
		sesion.setAttribute("dependentsInfoListModel", dependentsSession);

		return MyNumberJsp.REDIRECT_STAFF_REGIST_CONFIRM;
	}
}
