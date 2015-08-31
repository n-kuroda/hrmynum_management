package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.athuman.mynumber.web.dto.StaffInfoDto;
import com.athuman.mynumber.web.dto.StaffInfoResponseDto;
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.service.MyNumberService;
import com.athuman.mynumber.web.util.StringUtil;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
@SessionAttributes("staffInfoModel")
public class StaffExistCheckController {

	@Autowired(required=true)
	@Qualifier(value="myNumberService")
	private MyNumberService myNumberService;
	
	@RequestMapping(value = "/staffExistCheck", method = RequestMethod.GET)
	public String show(Model model) {
		
		StaffInfoDto staffInfoDto = new StaffInfoDto();
		model.addAttribute("staffInfoDto", staffInfoDto);
		return "staffExistCheck";
	}

	// submit staffExistCheck page
	@RequestMapping(value = "/staffExistCheck", method = RequestMethod.POST)
	public String search(StaffInfoDto staffInfoDtoForm, BindingResult bindingResult, Model model) {

		// check input value is valid or not
		if (ValidateUtil.checkInputValid("staffNo", staffInfoDtoForm.getStaffNo(), bindingResult, 9).hasErrors()) {
			model.addAttribute("staffInfoModel", new StaffInfoModel());
			return "staffExistCheck";
		}

		// call API to get data
		// FIXME: created dump data for displaying data on GUI
		StaffInfoResponseDto staffInfoResponseDto = myNumberService.readStaff(staffInfoDtoForm.getStaffNo());

		if (staffInfoResponseDto.getHttpStatus() == 200) { // OK

			StaffInfoDto staffInfoDto = staffInfoResponseDto.getStaffInfoDto();		

			model.addAttribute("staffNo", staffInfoDtoForm.getStaffNo());
			model.addAttribute("staffInfo", getStaffInfo(staffInfoDto));

			// convert data from StaffInfoDto to StaffInfoModel
			StaffInfoModel staffInfoModel = new StaffInfoModel();
			staffInfoModel.setStaffName(staffInfoDto.getStaffName());
			staffInfoModel.setStaffNameKana(staffInfoDto.getStaffNameKana());
			staffInfoModel.setStaffNo(staffInfoDto.getStaffNo());
			// store to session
			model.addAttribute("staffInfoModel", staffInfoModel);

		} else if (staffInfoResponseDto.getHttpStatus() == 204) { // error 204

			model.addAttribute("staffInfoModel", new StaffInfoModel());
			bindingResult.rejectValue("staffNo", "I00001",
					new Object[] {}, null);
		} else { // other error
			model.addAttribute("staffInfoModel", new StaffInfoModel());
			bindingResult.rejectValue("staffNo", "S00001",
					new Object[] { staffInfoDtoForm.getStaffNo() }, null);
		}

		return "staffExistCheck";
	}

	@RequestMapping(value = "/staffnext", method = RequestMethod.POST)
	public String next(StaffInfoDto staffInfoDtoForm, BindingResult bindingResult, Model model, HttpSession session) {

		StaffInfoModel staffInfoModel = (StaffInfoModel)session.getAttribute("staffInfoModel");

		if (staffInfoModel!= null &&
				StringUtil.isNotEmpty(staffInfoModel.getStaffNo())) {

			return "redirect:/purposeConsent";
		} else {
			bindingResult.rejectValue("staffNo", "V00001", new Object [] {"staffNo"}, null);
			return "staffExistCheck";
		}
	}

	@RequestMapping(value = "/staffback", method = RequestMethod.POST)
	public String back(Model model, HttpSession sesion) {

		sesion.invalidate();
		return "redirect:/shainExistCheck";

	}

	/** get staff info*/
	private String getStaffInfo(StaffInfoDto staffInfoDto) {
		String staffInfo = staffInfoDto.getStaffName() +
				"(" + staffInfoDto.getStaffNameKana() + ")";
		return staffInfo;
	}

	public void setMyNumberService(MyNumberService myNumberService) {
		this.myNumberService = myNumberService;
	}
}
