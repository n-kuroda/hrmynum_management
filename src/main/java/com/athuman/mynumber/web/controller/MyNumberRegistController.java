package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.athuman.mynumber.web.dto.MyNumberRegistDto;
import com.athuman.mynumber.web.model.StaffInfoModel;

@Controller
@SessionAttributes("myNumerRegistModel")
public class MyNumberRegistController {

	// show myNumberRegist page
	@RequestMapping(value = "/myNumberRegist", method = RequestMethod.GET)
	public String show(Model model, HttpSession session) {

		StaffInfoModel staffInfoModel = (StaffInfoModel)session.getAttribute("myNumerRegistModel");
		if (staffInfoModel == null) {
			staffInfoModel = new StaffInfoModel();
		}

		model.addAttribute("myNumberRegistDto", staffInfoModel);
		return "myNumberRegist";
	}

	// submit myNumberRegist page
	@RequestMapping(value = "/myNumberRegist", method = RequestMethod.POST)
	public String doNext(@Valid MyNumberRegistDto myNumber, BindingResult binding, Model model) {

		if (binding.hasErrors()) { // when form has error
			return "myNumberRegist";
		}

		// TODO store data to session
		StaffInfoModel staffInfoModel = processData(myNumber);
		model.addAttribute("myNumerRegistModel", staffInfoModel);

		// check at last one check box selected
		if (checkSelect(staffInfoModel)) {
			binding.rejectValue("other","Verification.myNumberRegistDto.verification");
			return "myNumberRegist";
		}

		return "redirect:/partnerRegist";

	}

	/** process data for store session
	 *
	 * @param myNumber
	 * @return MyNumberRegistDto
	 */
	private StaffInfoModel processData(MyNumberRegistDto myNumber) {
		StaffInfoModel mnRegist = new StaffInfoModel();
		if (myNumber != null) {

			// store my number
			if (myNumber.getMyNumber() != null) {
				mnRegist.setMyNumber(myNumber.getMyNumber());
			}

			// store MyNumber confirm
			if (myNumber.getMyNumberConfirm() != null) {
				mnRegist.setMyNumberConfirm(myNumber.getMyNumberConfirm());
			}

			// store driver license
			if (myNumber.getDriversLicense() != null) {
				mnRegist.setDriversLicense(myNumber.getDriversLicense());
			} else {
				mnRegist.setDriversLicense("0");
			}

			// store drive history license
			if (myNumber.getDriveHistoryLicense() != null) {
				mnRegist.setDriveHistoryLicense(myNumber.getDriveHistoryLicense());
			} else {
				mnRegist.setDriveHistoryLicense("0");
			}

			// store passport
			if (myNumber.getPassPort() != null) {
				mnRegist.setPassPort(myNumber.getPassPort());
			} else {
				mnRegist.setPassPort("0");
			}

			// store body disabilities notebook
			if (myNumber.getBodyDisabilitiesNotebook() != null) {
				mnRegist.setBodyDisabilitiesNotebook(myNumber.getBodyDisabilitiesNotebook());
			} else {
				mnRegist.setBodyDisabilitiesNotebook("0");
			}

			// store mental disabilities notebook
			if (myNumber.getMentalDisabilitiesNotebook() != null) {
				mnRegist.setMentalDisabilitiesNotebook(myNumber.getMentalDisabilitiesNotebook());
			} else {
				mnRegist.setMentalDisabilitiesNotebook("0");
			}

			// store rehabilitation notebook
			if (myNumber.getRehabilitationNotebook() != null) {
				mnRegist.setRehabilitationNotebook(myNumber.getRehabilitationNotebook());
			} else {
				mnRegist.setRehabilitationNotebook("0");
			}

			// store stay card
			if (myNumber.getStayCard() != null) {
				mnRegist.setStayCard(myNumber.getStayCard());
			} else {
				mnRegist.setStayCard("0");
			}

			// store clear Person
			if (myNumber.getClearPerson() != null) {
				mnRegist.setClearPerson(myNumber.getClearPerson());
			} else {
				mnRegist.setClearPerson("0");
			}

			// store insurance card license
			if (myNumber.getHealthInsuranceLicense() != null) {
				mnRegist.setHealthInsuranceLicense(myNumber.getHealthInsuranceLicense());
			} else {
				mnRegist.setHealthInsuranceLicense("0");
			}

			// store pension book
			if (myNumber.getPensionNotebook() != null) {
				mnRegist.setPensionNotebook(myNumber.getPensionNotebook());
			} else {
				mnRegist.setPensionNotebook("0");
			}

			// store other
			if (myNumber.getOther() != null) {
				mnRegist.setOther(myNumber.getOther());
			} else {
				mnRegist.setOther("0");
			}
		}

		return mnRegist;
	}

	/** Check which radio is checked
	 *
	 * @param staffInfoModel
	 * @return boolean
	 */
	private boolean checkSelect(StaffInfoModel staffInfoModel){
		if (!"01".equals(staffInfoModel.getMyNumberConfirm())) {
			if (checkVerification(staffInfoModel)) {
				return true;
			}
		}
		return false;
	}

	/** check at least one check box selected
	 *
	 * @param staffInfoModel
	 * @return boolean
	 */
	private boolean checkVerification(StaffInfoModel staffInfoModel){

		boolean flag = true;
		int checkTime = 0;
		// check driver license
		if ("1".equals(staffInfoModel.getDriversLicense())) {
			flag = false;
		}

		// check driving history license
		if ("1".equals(staffInfoModel.getDriveHistoryLicense())) {
			flag = false;
		}

		// check passport
		if ("1".equals(staffInfoModel.getPassPort())) {
			flag = false;
		}

		// check body disabilities notebook
		if ("1".equals(staffInfoModel.getBodyDisabilitiesNotebook())) {
			flag = false;
		}

		// check mental disabilities notebook
		if ("1".equals(staffInfoModel.getMentalDisabilitiesNotebook())) {
			flag = false;
		}

		// check rehabilitation notebook
		if ("1".equals(staffInfoModel.getRehabilitationNotebook())) {
			flag = false;
		}

		// check stay card
		if ("1".equals(staffInfoModel.getStayCard())) {
			flag = false;
		}

		// check clear person
		if ("1".equals(staffInfoModel.getClearPerson())) {
			flag = false;
		}

		if (!flag) {
			// check health insurance license
			if ("1".equals(staffInfoModel.getHealthInsuranceLicense())) {
				flag = false;
				checkTime ++;
			}

			// check pension book
			if ("1".equals(staffInfoModel.getPensionNotebook())) {
				flag = false;
				checkTime ++;
			}

			// check other
			if ("1".equals(staffInfoModel.getOther())) {
				flag = false;
				checkTime ++;
			}

			if (checkTime <= 1) {
				flag = true;
			}
		}
		return flag;
	}
}
