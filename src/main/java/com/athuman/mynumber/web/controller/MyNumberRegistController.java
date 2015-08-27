package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.dto.MyNumberRegistDto;

@Controller
public class MyNumberRegistController {

	// inject staffExistCheckDto
	private MyNumberRegistDto myNumberRegistDto;

	@RequestMapping(value = "/myNumberRegist", method = RequestMethod.GET)
	public String show(Model model, HttpSession session) {

		if (myNumberRegistDto != null) {
			model.addAttribute("myNumberRegistDto", myNumberRegistDto);
		} else {
			myNumberRegistDto = new MyNumberRegistDto();
			model.addAttribute("myNumberRegistDto", myNumberRegistDto);
		}

		return "myNumberRegist";
	}

	// submit myNumberRegist page
	@RequestMapping(value = "/myNumberRegist", method = RequestMethod.POST)
	public String doNext(@Valid MyNumberRegistDto myNumber, BindingResult binding, Model model) {

		if (binding.hasErrors()) { // when form has error
			return "myNumberRegist";
		} else { // success

			// TODO store data to session
			myNumberRegistDto = processData(myNumber);

			// check at last one check box selected
			if (checkSelect(myNumberRegistDto)) {
				binding.rejectValue("other","Verification.myNumberRegistDto.verification");
				return "myNumberRegist";
			}

			return "redirect:/myNumberRegist";
		}
	}

	/** process data for store session
	 *
	 * @param myNumber
	 * @return
	 */
	private MyNumberRegistDto processData(MyNumberRegistDto myNumber){
		MyNumberRegistDto mnRegist = new MyNumberRegistDto();
		if (myNumber != null) {
			// store my number
			if (myNumber.getMyNumber() != null) {
				mnRegist.setMyNumber(myNumber.getMyNumber());
			}

			// store card info
			if (myNumber.getCardInfo() != null) {
				mnRegist.setCardInfo(myNumber.getCardInfo());
			}

			// store driver license
			if (myNumber.getDriverLicense() != null) {
				mnRegist.setDriverLicense(myNumber.getDriverLicense());
			} else {
				mnRegist.setDriverLicense("0");
			}

			// store driving career
			if (myNumber.getDrivingExperience() != null) {
				mnRegist.setDrivingExperience(myNumber.getDrivingExperience());
			} else {
				mnRegist.setDrivingExperience("0");
			}

			// store passport
			if (myNumber.getPassport() != null) {
				mnRegist.setPassport(myNumber.getPassport());
			} else {
				mnRegist.setPassport("0");
			}

			// store health insurance
			if (myNumber.getHealthInsurance() != null) {
				mnRegist.setHealthInsurance(myNumber.getHealthInsurance());
			} else {
				mnRegist.setHealthInsurance("0");
			}

			// store insurance handbook
			if (myNumber.getInsuranceHandbook() != null) {
				mnRegist.setInsuranceHandbook(myNumber.getInsuranceHandbook());
			} else {
				mnRegist.setInsuranceHandbook("0");
			}

			// store manual care
			if (myNumber.getManualCare() != null) {
				mnRegist.setManualCare(myNumber.getManualCare());
			} else {
				mnRegist.setManualCare("0");
			}

			// store stay card
			if (myNumber.getStayCard() != null) {
				mnRegist.setStayCard(myNumber.getStayCard());
			} else {
				mnRegist.setStayCard("0");
			}

			// store insurance card
			if (myNumber.getInsuranceCard() != null) {
				mnRegist.setInsuranceCard(myNumber.getInsuranceCard());
			} else {
				mnRegist.setInsuranceCard("0");
			}

			// store pension book
			if (myNumber.getPensionBook() != null) {
				mnRegist.setPensionBook(myNumber.getPensionBook());
			} else {
				mnRegist.setPensionBook("0");
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
	 * @param myNumberRegistDto
	 * @return
	 */
	private boolean checkSelect(MyNumberRegistDto myNumberRegistDto){
		if (!"01".equals(myNumberRegistDto.getCardInfo())) {
			if (checkVerification(myNumberRegistDto)) {
				return true;
			}
		}
		return false;
	}

	/** check at least one check box selected
	 *
	 * @param myNumberRegistDto
	 * @return
	 */
	private boolean checkVerification(MyNumberRegistDto myNumberRegistDto){

		// check driver license
		if ("1".equals(myNumberRegistDto.getDriverLicense())) {
			return false;
		}

		// check driving career
		if ("1".equals(myNumberRegistDto.getDrivingExperience())) {
			return false;
		}

		// check passport
		if ("1".equals(myNumberRegistDto.getPassport())) {
			return false;
		}

		// check health insurance
		if ("1".equals(myNumberRegistDto.getHealthInsurance())) {
			return false;
		}

		// check insurance handbook
		if ("1".equals(myNumberRegistDto.getInsuranceHandbook())) {
			return false;
		}

		// check manual care
		if ("1".equals(myNumberRegistDto.getManualCare())) {
			return false;
		}

		// check stay card
		if ("1".equals(myNumberRegistDto.getStayCard())) {
			return false;
		}

		// check insurance card
		if ("1".equals(myNumberRegistDto.getInsuranceCard())) {
			return false;
		}

		// check pension book
		if ("1".equals(myNumberRegistDto.getPensionBook())) {
			return false;
		}

		// check other
		if ("1".equals(myNumberRegistDto.getOther())) {
			return false;
		}
		return true;
	}
}
