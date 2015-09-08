package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.dto.StaffInfoDto;
import com.athuman.mynumber.web.dto.StaffInfoResponseDto;
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.service.MyNumberService;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.StringUtil;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
public class StaffExistCheckController {

	@Autowired(required=true)
	@Qualifier(value="myNumberService")
	private MyNumberService myNumberService;

	@RequestMapping(value = MyNumberUrl.STAFF_EXIST_CHECK, method = RequestMethod.GET)
	public String show(Model model) {

		model.addAttribute("staffInfoModel", new StaffInfoModel());
		return MyNumberJsp.STAFF_EXIST_CHECK;
	}

	// submit staffExistCheck page
	@RequestMapping(value = MyNumberUrl.STAFF_EXIST_CHECK, method = RequestMethod.POST)
	public String search(StaffInfoModel staffInfoModelForm, BindingResult bindingResult,
			Model model, HttpSession session) {

		// check input value is valid or not
		if (ValidateUtil.checkInputValid("staffNo", "スタッフNo", staffInfoModelForm.getStaffNo(), bindingResult, 9).hasErrors()) {
			session.setAttribute("staffInfoModel", null);
			return MyNumberJsp.STAFF_EXIST_CHECK;
		}

		// call API to get data
		// FIXME: created dump data for displaying data on GUI
		StaffInfoResponseDto staffInfoResponseDto = myNumberService.readStaff(staffInfoModelForm.getStaffNo());

		if (staffInfoResponseDto.getHttpStatus() == 200) { // OK

			StaffInfoDto staffInfoDto = staffInfoResponseDto.getStaffInfoDto();

			model.addAttribute("staffNo", staffInfoModelForm.getStaffNo());
			model.addAttribute("staffInfo", getStaffInfo(staffInfoDto));

			// convert data from StaffInfoDto to StaffInfoModel
			StaffInfoModel staffInfoModel = new StaffInfoModel();

			staffInfoModel.setStaffNameSei(staffInfoDto.getNameSei());
			staffInfoModel.setStaffNameMei(staffInfoDto.getNameMei());
			staffInfoModel.setStaffNameSeiKana(staffInfoDto.getNameKanaSei());
			staffInfoModel.setStaffNameMeiKana(staffInfoDto.getNameKanaMei());
			staffInfoModel.setStaffNo(staffInfoModelForm.getStaffNo());

			// store to session
			session.setAttribute("staffInfoModel", staffInfoModel);

		} else if (staffInfoResponseDto.getHttpStatus() == 204) { // error 204

			session.setAttribute("staffInfoModel", null);
			bindingResult.rejectValue("staffNo", "I00001",
					new Object[] {}, null);
		} else { // other error
			session.setAttribute("staffInfoModel", null);
			bindingResult.rejectValue("staffNo", "S00001",
					new Object[] {"スタッフNo"}, null);
		}

		return MyNumberJsp.STAFF_EXIST_CHECK;
	}

	@RequestMapping(value = MyNumberUrl.NEXT_TO_PURPOSE_CONSENT, method = RequestMethod.POST)
	public String next(StaffInfoModel staffInfoModelForm, BindingResult bindingResult, 
			Model model, HttpSession session) {

		StaffInfoModel staffInfoModel = (StaffInfoModel)session.getAttribute("staffInfoModel");

		if (staffInfoModel!= null &&
				StringUtil.isNotEmpty(staffInfoModel.getStaffNo())) {

			return MyNumberJsp.REDIRECT_PURPOSE_CONSENT;
		} else {
			bindingResult.rejectValue("staffNo", "V00001", new Object [] {"スタッフNo"}, null);
			return MyNumberJsp.STAFF_EXIST_CHECK;
		}
	}

	@RequestMapping(value = MyNumberUrl.BACK_TO_SHAIN_EXIST_CHECK, method = RequestMethod.POST)
	public String back(Model model) {
		return MyNumberJsp.REDIRECT_SHAIN_EXIST_CHECK;
	}

	/** get staff info*/
	private String getStaffInfo(StaffInfoDto staffInfoDto) {
		String staffInfo = staffInfoDto.getNameSei() + " " + staffInfoDto.getNameMei() + 
				"(" + staffInfoDto.getNameKanaSei() + " " + staffInfoDto.getNameKanaMei() + ")";
		return staffInfo;
	}

	public void setMyNumberService(MyNumberService myNumberService) {
		this.myNumberService = myNumberService;
	}
}
