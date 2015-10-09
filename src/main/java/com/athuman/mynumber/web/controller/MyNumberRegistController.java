package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.util.ConstValues;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.StringUtil;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
public class MyNumberRegistController {

	private StaffInfoModel staffInfoModel;
	// show myNumberRegist page
	@RequestMapping(value = MyNumberUrl.MYNUMBER_REGIST, method = RequestMethod.GET)
	public String show(Model model, HttpSession session) {

		// check session has exist
		if (!ValidateUtil.isNotNullSession(session, model)) {
			return MyNumberJsp.REDIRECT_SHAIN_EXIST_CHECK;
		}
	 	
		staffInfoModel = (StaffInfoModel)session.getAttribute("staffInfoModel");
		if (staffInfoModel == null) {
			staffInfoModel = new StaffInfoModel();
		}

		model.addAttribute("staffInfoModel", staffInfoModel);
		return MyNumberJsp.MYNUMBER_REGIST;
	}

	// submit myNumberRegist page
	@RequestMapping(value = MyNumberUrl.MYNUMBER_REGIST, method = RequestMethod.POST)
	public String next(@ModelAttribute("staffInfoModel") StaffInfoModel myNumberForm,
			BindingResult binding, Model model, HttpSession session, WebRequest request) {

		if (ValidateUtil.checkInputValid("myNumber", "マイナンバー", myNumberForm.getMyNumber(), binding, 12).hasErrors()) { // when form has error
			return MyNumberJsp.MYNUMBER_REGIST;
		}
		if (!StringUtil.isNotEmpty(myNumberForm.getMyNumberConfirm())) {
			binding.rejectValue("myNumberConfirm", "V00001", new Object [] {"マイナンバー確認提示書類"}, null);
			return MyNumberJsp.MYNUMBER_REGIST;
		}
		// store data to session
		staffInfoModel = processData(myNumberForm, staffInfoModel);
		session.setAttribute("staffInfoModel", staffInfoModel);

		// check at last one check box selected
		if (checkSelect(staffInfoModel)) {
			binding.rejectValue("other","V00004");
			return MyNumberJsp.MYNUMBER_REGIST;
		}

		return MyNumberJsp.REDIRECT_DEPENDENTS_REGIST;

	}

	// submit myNumberRegist page
	@RequestMapping(value = MyNumberUrl.BACK_TO_PURPOSE_CONSENT, method = RequestMethod.POST)
	public String back(@ModelAttribute("staffInfoModel") StaffInfoModel myNumberForm,
			BindingResult binding, Model model, WebRequest request) {
		return MyNumberJsp.REDIRECT_PURPOSE_CONSENT;
	}

	/** process data for store session
	 *
	 * @param myNumber
	 * @return StaffInfoModel
	 */
	private StaffInfoModel processData(StaffInfoModel myNumberForm, StaffInfoModel staffInfoModel) {
		if (myNumberForm != null) {

			// store my number
			if (StringUtil.isNotEmpty(myNumberForm.getMyNumber())) {
				staffInfoModel.setMyNumber(myNumberForm.getMyNumber());
			}

			// store MyNumber confirm
			if (StringUtil.isNotEmpty(myNumberForm.getMyNumberConfirm())) {
				staffInfoModel.setMyNumberConfirm(myNumberForm.getMyNumberConfirm());
			}

			// store driver license
			if (StringUtil.isNotEmpty(myNumberForm.getDriversLicense()) &&
					!ConstValues.RADIO_SELECT.equals(myNumberForm.getMyNumberConfirm())) {
				staffInfoModel.setDriversLicense(myNumberForm.getDriversLicense());
			} else {
				staffInfoModel.setDriversLicense(ConstValues.CHECKBOX_NOT_SELECT);
			}

			// store drive history license
			if (StringUtil.isNotEmpty(myNumberForm.getDriveHistoryLicense()) &&
					!ConstValues.RADIO_SELECT.equals(myNumberForm.getMyNumberConfirm())) {
				staffInfoModel.setDriveHistoryLicense(myNumberForm.getDriveHistoryLicense());
			} else {
				staffInfoModel.setDriveHistoryLicense(ConstValues.CHECKBOX_NOT_SELECT);
			}

			// store passport
			if (StringUtil.isNotEmpty(myNumberForm.getPassPort()) &&
					!ConstValues.RADIO_SELECT.equals(myNumberForm.getMyNumberConfirm())) {
				staffInfoModel.setPassPort(myNumberForm.getPassPort());
			} else {
				staffInfoModel.setPassPort(ConstValues.CHECKBOX_NOT_SELECT);
			}

			// store body disabilities notebook
			if (StringUtil.isNotEmpty(myNumberForm.getBodyDisabilitiesNotebook()) &&
					!ConstValues.RADIO_SELECT.equals(myNumberForm.getMyNumberConfirm())) {
				staffInfoModel.setBodyDisabilitiesNotebook(myNumberForm.getBodyDisabilitiesNotebook());
			} else {
				staffInfoModel.setBodyDisabilitiesNotebook(ConstValues.CHECKBOX_NOT_SELECT);
			}

			// store mental disabilities notebook
			if (StringUtil.isNotEmpty(myNumberForm.getMentalDisabilitiesNotebook()) &&
					!ConstValues.RADIO_SELECT.equals(myNumberForm.getMyNumberConfirm())) {
				staffInfoModel.setMentalDisabilitiesNotebook(myNumberForm.getMentalDisabilitiesNotebook());
			} else {
				staffInfoModel.setMentalDisabilitiesNotebook(ConstValues.CHECKBOX_NOT_SELECT);
			}

			// store rehabilitation notebook
			if (StringUtil.isNotEmpty(myNumberForm.getRehabilitationNotebook()) &&
					!ConstValues.RADIO_SELECT.equals(myNumberForm.getMyNumberConfirm())) {
				staffInfoModel.setRehabilitationNotebook(myNumberForm.getRehabilitationNotebook());
			} else {
				staffInfoModel.setRehabilitationNotebook(ConstValues.CHECKBOX_NOT_SELECT);
			}

			// store stay card
			if (StringUtil.isNotEmpty(myNumberForm.getStayCard()) &&
					!ConstValues.RADIO_SELECT.equals(myNumberForm.getMyNumberConfirm())) {
				staffInfoModel.setStayCard(myNumberForm.getStayCard());
			} else {
				staffInfoModel.setStayCard(ConstValues.CHECKBOX_NOT_SELECT);
			}

			// store insurance card license
			if (StringUtil.isNotEmpty(myNumberForm.getHealthInsuranceLicense()) &&
					!ConstValues.RADIO_SELECT.equals(myNumberForm.getMyNumberConfirm())) {
				staffInfoModel.setHealthInsuranceLicense(myNumberForm.getHealthInsuranceLicense());
			} else {
				staffInfoModel.setHealthInsuranceLicense(ConstValues.CHECKBOX_NOT_SELECT);
			}

			// store pension book
			if (StringUtil.isNotEmpty(myNumberForm.getPensionNotebook()) &&
					!ConstValues.RADIO_SELECT.equals(myNumberForm.getMyNumberConfirm())) {
				staffInfoModel.setPensionNotebook(myNumberForm.getPensionNotebook());
			} else {
				staffInfoModel.setPensionNotebook(ConstValues.CHECKBOX_NOT_SELECT);
			}

			// store other
			if (StringUtil.isNotEmpty(myNumberForm.getOther()) &&
					!ConstValues.RADIO_SELECT.equals(myNumberForm.getMyNumberConfirm())) {
				staffInfoModel.setOther(myNumberForm.getOther());
			} else {
				staffInfoModel.setOther(ConstValues.CHECKBOX_NOT_SELECT);
			}
		}

		return staffInfoModel;
	}

	/** Check which radio is checked
	 *
	 * @param staffInfoModel
	 * @return boolean
	 */
	private boolean checkSelect(StaffInfoModel staffInfoModel){
		if (!ConstValues.RADIO_SELECT.equals(staffInfoModel.getMyNumberConfirm())) {
			return checkVerification(staffInfoModel);
		}
		return false;
	}

	/** check at least one check box selected
	 *
	 * @param staffInfoModel
	 * @return boolean
	 */
	private boolean checkVerification(StaffInfoModel staffInfoModel){

		int checkTime = 0;

		// check at least 1 checkbox is selected from 1-7
		if (ConstValues.CHECKBOX_SELECT.equals(staffInfoModel.getDriversLicense()) ||
				ConstValues.CHECKBOX_SELECT.equals(staffInfoModel.getDriveHistoryLicense()) ||
				ConstValues.CHECKBOX_SELECT.equals(staffInfoModel.getPassPort()) ||
				ConstValues.CHECKBOX_SELECT.equals(staffInfoModel.getBodyDisabilitiesNotebook()) ||
				ConstValues.CHECKBOX_SELECT.equals(staffInfoModel.getMentalDisabilitiesNotebook()) ||
				ConstValues.CHECKBOX_SELECT.equals(staffInfoModel.getRehabilitationNotebook()) ||
				ConstValues.CHECKBOX_SELECT.equals(staffInfoModel.getStayCard())) {
			return false;
		} else { // in case checkbox from 8-10 is selected

			// check health insurance license
			if (ConstValues.CHECKBOX_SELECT.equals(staffInfoModel.getHealthInsuranceLicense())) {
				checkTime ++;
			}

			// check pension book
			if (ConstValues.CHECKBOX_SELECT.equals(staffInfoModel.getPensionNotebook())) {
				checkTime ++;
			}

			// check other
			if (ConstValues.CHECKBOX_SELECT.equals(staffInfoModel.getOther())) {
				checkTime ++;
			}
		}

		// show error in case there are less than 2 check-boxes checked.
		if (checkTime < ConstValues.MYNUMBER_REGIST_MAX_CHECKBOX_CHECKED) {
			return true;
		} else {
			return false;
		}
	}
}
