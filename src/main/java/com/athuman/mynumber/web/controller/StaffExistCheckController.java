package com.athuman.mynumber.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String search(@RequestParam String action, StaffExistCheckDto staff, BindingResult bindingResult, Model model) {

		// detect the action is [search] or not
		if ("search".equals(action)) {

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
				bindingResult.rejectValue("staffNo", "NotExist.staffExistCheckDto.staffNo");
				staffExistCheckDto = new StaffExistCheckDto();
			}
			return "staffExistCheck";

		} else if("back".equals(action)) { // [back] btn clicked

			return "redirect:/shainExistCheck";

		} else { // [next] btn clicked

			return "purposeConsent";

		}
	}

	/** get employee info*/
	private String getStaffInfo() {
		String staffInfo = staffExistCheckDto.getStaffName() +
				"(" + staffExistCheckDto.getStaffNameKana() + ")";
		return "お名前：" + staffInfo;
	}

	/** call API to get data
	 *
	 * @Input:  staffNo
	 *
	 * */
	private int callAPIx(String staffNo) {
		if ("154310892".equals(staffNo)) { // dummy employeeId
			return 1;
		}
		return 0;
	}

	/** create dummy data */
	private StaffExistCheckDto getDataFromAPI() {

		StaffExistCheckDto staffExistCheckDto = new StaffExistCheckDto();
		staffExistCheckDto.setStaffName("Ly Ngan");
		staffExistCheckDto.setStaffNameKana("Nham Ngoc");
		staffExistCheckDto.setStaffNo("154310892");

		return staffExistCheckDto;
	}
}
