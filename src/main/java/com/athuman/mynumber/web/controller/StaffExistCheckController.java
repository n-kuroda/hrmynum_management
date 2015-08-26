package com.athuman.mynumber.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.dto.StaffExistCheckDto;

@Controller
public class StaffExistCheckController {


	// inject StaffExistCheckDto
	private StaffExistCheckDto staffExistCheckDto;

	@RequestMapping(value = "/staffExistCheck", method = RequestMethod.GET)
	public String show(Model model) {

		staffExistCheckDto = new StaffExistCheckDto();
		model.addAttribute("staffExistCheckDto", staffExistCheckDto);
		return "staffExistCheck";
	}

	// submit staffExistCheck page
	@RequestMapping(value = "/staffExistCheck", method = RequestMethod.POST)
	public String search(@Valid StaffExistCheckDto staff, BindingResult bindingResult, Model model) {

		// check input value is valid or not
		if (bindingResult.hasErrors()) {
			staffExistCheckDto = new StaffExistCheckDto();
			return "staffExistCheck";
		}

		// call API to get data
		if (callAPIx(staff.getStaffNo()) == 1) { // success

			// FIXME: created dump data for displaying data on GUI
			staffExistCheckDto = getDataFromAPI();

			model.addAttribute("staffInfo", getStaffInfo());
			model.addAttribute("staffExistCheckDto", staffExistCheckDto);

		} else { // failed

			staffExistCheckDto = new StaffExistCheckDto();
			bindingResult.rejectValue("staffNo", "NotExist.staffExistCheckDto.staffNo");
		}

		return "staffExistCheck";
	}

	@RequestMapping(value = "/staffnext", method = RequestMethod.POST)
	public String next(StaffExistCheckDto staff, BindingResult bindingResult, Model model) {

		if (staffExistCheckDto!= null && staffExistCheckDto.getStaffNo() != null) {
			return "redirect:/purposeConsent";
		} else {
			bindingResult.rejectValue("staffNo", "Session.staffExistCheckDto.staffNo");
			staffExistCheckDto = new StaffExistCheckDto();
			return "staffExistCheck";
		}
	}

	@RequestMapping(value = "/staffback", method = RequestMethod.POST)
	public String back(Model model) {

		return "redirect:/shainExistCheck";

	}

	/** get staff info*/
	private String getStaffInfo() {
		String staffInfo = staffExistCheckDto.getStaffName() +
				"(" + staffExistCheckDto.getStaffNameKana() + ")";
		return staffInfo;
	}

	/** call API to get data
	 *
	 * @Input:  staffNo
	 *
	 * */
	private int callAPIx(String staffNo) {
		if ("154123456".equals(staffNo)) { // dummy StaffNo
			return 1;
		}
		return 0;
	}

	/** create dummy data */
	private StaffExistCheckDto getDataFromAPI() {

		StaffExistCheckDto staffExistCheckDto = new StaffExistCheckDto();
		staffExistCheckDto.setStaffName("Ly Ngan");
		staffExistCheckDto.setStaffNameKana("Nham Ngoc");
		staffExistCheckDto.setStaffNo("154123456");

		return staffExistCheckDto;
	}
}
