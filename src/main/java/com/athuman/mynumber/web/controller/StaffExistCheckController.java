package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.athuman.mynumber.web.dto.StaffInfoDto;
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.service.MyNumberService;
import com.athuman.mynumber.web.util.StringUtil;

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
	public String search(@Valid StaffInfoDto staffInfoDtoForm, BindingResult bindingResult, Model model) {

		// check input value is valid or not
		if (bindingResult.hasErrors()) {
			model.addAttribute("staffInfoModel", new StaffInfoModel());
			return "staffExistCheck";
		}

		// call API to get data
		// FIXME: created dump data for displaying data on GUI
		StaffInfoDto staffInfoDto = myNumberService.readStaff(staffInfoDtoForm.getStaffNo());
		if (staffInfoDto != null) { // success

			model.addAttribute("staffInfo", getStaffInfo(staffInfoDto));

			// convert data from StaffInfoDto to StaffInfoModel
			StaffInfoModel staffInfoModel = new StaffInfoModel();
			staffInfoModel.setStaffName(staffInfoDto.getStaffName());
			staffInfoModel.setStaffNameKana(staffInfoDto.getStaffNameKana());
			staffInfoModel.setStaffNo(staffInfoDto.getStaffNo());

			model.addAttribute("staffInfoModel", staffInfoModel);

		} else { // failed

			model.addAttribute("staffInfoModel", new StaffInfoModel());
			bindingResult.rejectValue("staffNo", "NotExist.staffInfoDto.staffNo");
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
			bindingResult.rejectValue("staffNo", "Session.staffInfoDto.staffNo");
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
