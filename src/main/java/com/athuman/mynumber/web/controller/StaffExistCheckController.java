package com.athuman.mynumber.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.athuman.mynumber.web.dto.StaffInfoModel;
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

		StaffInfoModel staffInfoModel = new StaffInfoModel();
		model.addAttribute("staffInfoModel", staffInfoModel);
		return "staffExistCheck";
	}

	// submit staffExistCheck page
	@RequestMapping(value = "/staffExistCheck", method = RequestMethod.POST)
	public String search(@Valid StaffInfoModel staffInfoModel, BindingResult bindingResult, Model model) {

		// check input value is valid or not
		if (bindingResult.hasErrors()) {
			staffInfoModel = new StaffInfoModel();
			return "staffExistCheck";
		}

		// call API to get data
		// FIXME: created dump data for displaying data on GUI
		staffInfoModel = myNumberService.readStaff(staffInfoModel.getStaffNo());
		if (staffInfoModel != null) { // success

			model.addAttribute("staffInfo", getStaffInfo(staffInfoModel));
			model.addAttribute("staffInfoModel", staffInfoModel);

		} else { // failed

			staffInfoModel = new StaffInfoModel();
			bindingResult.rejectValue("staffNo", "NotExist.staffInfoModel.staffNo");
		}

		return "staffExistCheck";
	}

	@RequestMapping(value = "/staffnext", method = RequestMethod.POST)
	public String next(StaffInfoModel staffInfoModel, BindingResult bindingResult, Model model) {

		if (staffInfoModel!= null && 
				StringUtil.isNotEmpty(staffInfoModel.getStaffNo())) {

			return "redirect:/purposeConsent";
		} else {
			bindingResult.rejectValue("staffNo", "Session.staffInfoModel.staffNo");
			staffInfoModel = new StaffInfoModel();
			return "staffExistCheck";
		}
	}

	@RequestMapping(value = "/staffback", method = RequestMethod.POST)
	public String back(Model model) {

		return "redirect:/shainExistCheck";

	}

	/** get staff info*/
	private String getStaffInfo(StaffInfoModel staffInfoModel) {
		String staffInfo = staffInfoModel.getStaffName() +
				"(" + staffInfoModel.getStaffNameKana() + ")";
		return staffInfo;
	}
	
	public void setMyNumberService(MyNumberService myNumberService) {
		this.myNumberService = myNumberService;
	}
}
