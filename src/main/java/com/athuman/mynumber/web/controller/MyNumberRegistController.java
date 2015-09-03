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
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.StringUtil;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
@SessionAttributes("myNumerRegistModel")
public class MyNumberRegistController {

	// show myNumberRegist page
	@RequestMapping(value = MyNumberUrl.MYNUMBER_REGIST, method = RequestMethod.GET)
	public String show(Model model, HttpSession session) {

		StaffInfoModel staffInfoModel = (StaffInfoModel)session.getAttribute("myNumerRegistModel");
		if (staffInfoModel == null) {
			staffInfoModel = new StaffInfoModel();
		}

		model.addAttribute("myNumberRegistDto", staffInfoModel);
		return MyNumberJsp.MYNUMBER_REGIST;
	}

	// submit myNumberRegist page
	@RequestMapping(value = MyNumberUrl.MYNUMBER_REGIST, method = RequestMethod.POST)
	public String doNext(@Valid MyNumberRegistDto myNumber, BindingResult binding, Model model) {

		if (ValidateUtil.checkInputValid("myNumber", "マイナンバー", myNumber.getMyNumber(), binding, 12).hasErrors()) { // when form has error
			return MyNumberJsp.MYNUMBER_REGIST;
		}
		if (!StringUtil.isNotEmpty(myNumber.getMyNumberConfirm())) {
			binding.rejectValue("myNumberConfirm", "V00001", new Object [] {"マイナンバー確認書類"}, null);
		}
		// store data to session
		StaffInfoModel staffInfoModel = processData(myNumber);
		model.addAttribute("myNumerRegistModel", staffInfoModel);

		// check at last one check box selected
		if (checkSelect(staffInfoModel)) {
			binding.rejectValue("other","V00004");
			return MyNumberJsp.MYNUMBER_REGIST;
		}

		return MyNumberJsp.REDIRECT_PARTNER_REGIST;

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
			if (StringUtil.isNotEmpty(myNumber.getMyNumber())) {
				mnRegist.setMyNumber(myNumber.getMyNumber());
			}

			// store MyNumber confirm
			if (StringUtil.isNotEmpty(myNumber.getMyNumberConfirm())) {
				mnRegist.setMyNumberConfirm(myNumber.getMyNumberConfirm());
			}

			// store driver license
			if (StringUtil.isNotEmpty(myNumber.getDriversLicense())) {
				mnRegist.setDriversLicense(myNumber.getDriversLicense());
			} else {
				mnRegist.setDriversLicense("0");
			}

			// store drive history license
			if (StringUtil.isNotEmpty(myNumber.getDriveHistoryLicense())) {
				mnRegist.setDriveHistoryLicense(myNumber.getDriveHistoryLicense());
			} else {
				mnRegist.setDriveHistoryLicense("0");
			}

			// store passport
			if (StringUtil.isNotEmpty(myNumber.getPassPort())) {
				mnRegist.setPassPort(myNumber.getPassPort());
			} else {
				mnRegist.setPassPort("0");
			}

			// store body disabilities notebook
			if (StringUtil.isNotEmpty(myNumber.getBodyDisabilitiesNotebook())) {
				mnRegist.setBodyDisabilitiesNotebook(myNumber.getBodyDisabilitiesNotebook());
			} else {
				mnRegist.setBodyDisabilitiesNotebook("0");
			}

			// store mental disabilities notebook
			if (StringUtil.isNotEmpty(myNumber.getMentalDisabilitiesNotebook())) {
				mnRegist.setMentalDisabilitiesNotebook(myNumber.getMentalDisabilitiesNotebook());
			} else {
				mnRegist.setMentalDisabilitiesNotebook("0");
			}

			// store rehabilitation notebook
			if (StringUtil.isNotEmpty(myNumber.getRehabilitationNotebook())) {
				mnRegist.setRehabilitationNotebook(myNumber.getRehabilitationNotebook());
			} else {
				mnRegist.setRehabilitationNotebook("0");
			}

			// store stay card
			if (StringUtil.isNotEmpty(myNumber.getStayCard())) {
				mnRegist.setStayCard(myNumber.getStayCard());
			} else {
				mnRegist.setStayCard("0");
			}

			// store clear Person
			if (StringUtil.isNotEmpty(myNumber.getClearPerson())) {
				mnRegist.setClearPerson(myNumber.getClearPerson());
			} else {
				mnRegist.setClearPerson("0");
			}

			// store insurance card license
			if (StringUtil.isNotEmpty(myNumber.getHealthInsuranceLicense())) {
				mnRegist.setHealthInsuranceLicense(myNumber.getHealthInsuranceLicense());
			} else {
				mnRegist.setHealthInsuranceLicense("0");
			}

			// store pension book
			if (StringUtil.isNotEmpty(myNumber.getPensionNotebook())) {
				mnRegist.setPensionNotebook(myNumber.getPensionNotebook());
			} else {
				mnRegist.setPensionNotebook("0");
			}

			// store other
			if (StringUtil.isNotEmpty(myNumber.getOther())) {
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
