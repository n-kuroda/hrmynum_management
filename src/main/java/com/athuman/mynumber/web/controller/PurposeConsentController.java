package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.athuman.mynumber.web.model.DependentsInfoListModel;
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.util.ConstValues;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
public class PurposeConsentController {

	@RequestMapping(value = MyNumberUrl.PURPOSE_CONSENT, method = RequestMethod.GET)
	public String show(Model model, @RequestParam("token") String requestToken, HttpSession sesion) {

		// check session has exist
		if (!ValidateUtil.isNotNullSession(sesion, model)) {
			return MyNumberJsp.REDIRECT_SHAIN_EXIST_CHECK;
		}
	 	
		StaffInfoModel staffInfoModelSession = (StaffInfoModel)sesion.getAttribute("staffInfoModel");
		model.addAttribute("staffInfo", staffInfoModelSession);
		model.addAttribute("token", requestToken);
		return MyNumberJsp.PURPOSE_CONSENT;
	}

	// submit purposeConsent page
	@RequestMapping(value = MyNumberUrl.PURPOSE_CONSENT, method = RequestMethod.POST)
	public String consent(Model model, 
			HttpSession session, 
			@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModel,
			BindingResult binding, 
			WebRequest request, 
			@RequestParam("token") String requestToken) {

		// Check token
		if (!ValidateUtil.isValidToken("", request, requestToken, binding, model)) {
			return resetData(model, session);
		}

		StaffInfoModel staffInfoModelSession = (StaffInfoModel)session.getAttribute("staffInfoModel");
		staffInfoModelSession.setConsent(ConstValues.CONSENT_VALUE_1);
		session.setAttribute("staffInfoModel", staffInfoModelSession);

		return MyNumberJsp.REDIRECT_MYNUMBER_REGIST;
	}

	@RequestMapping(value = MyNumberUrl.BACK_TO_STAFF_EXIST_CHECK, method = RequestMethod.POST)
	public String back(Model model, 
			HttpSession session,
			@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModel,
			BindingResult binding, 
			WebRequest request, 
			@RequestParam("token") String requestToken) {

		// Check token
		if (!ValidateUtil.isValidToken("", request, requestToken, binding, model)) {
			return resetData(model, session);
		}

		return MyNumberJsp.REDIRECT_STAFF_EXIST_CHECK;
	}

	@RequestMapping(value = MyNumberUrl.SKIP_TO_STAFF_REGIST_CONFIRM_SCREEN, method = RequestMethod.POST)
	public String other(Model model, 
			HttpSession session,
			@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModel, 
			HttpSession sesion,
			BindingResult binding, 
			WebRequest request, @RequestParam("token") String requestToken) {

		// Check token
		if (!ValidateUtil.isValidToken("", request, requestToken, binding, model)) {
			return resetData(model, session);
		}

		StaffInfoModel staffInfoModelSession = (StaffInfoModel)sesion.getAttribute("staffInfoModel");
		// clear staff info.
		staffInfoModelSession.setMyNumber(ConstValues.BLANK);
		staffInfoModelSession.setMyNumberConfirm(ConstValues.MY_NUMBER_CONFIRM_00);
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
	
	/**
	 * reset data when load page
	 *
	 * @param model
	 * @param session
	 * @return String
	 */
	private String resetData(Model model, HttpSession session){
		// get data form session.
		StaffInfoModel staffInfoModelSession = (StaffInfoModel)session.getAttribute("staffInfoModel");
		model.addAttribute("staffInfo", staffInfoModelSession);
		return MyNumberJsp.PURPOSE_CONSENT;
	}
}
