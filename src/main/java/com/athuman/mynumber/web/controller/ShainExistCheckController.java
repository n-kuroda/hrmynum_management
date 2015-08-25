package com.athuman.mynumber.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.dto.ShainExistCheckDto;

@Controller
public class ShainExistCheckController {

	// inject shainExistCheckDto
	private ShainExistCheckDto shainExistCheckDto;

	// display shainExistCheck page
	@RequestMapping(value = "/shainExistCheck", method = RequestMethod.GET)
	public String show(Model model) {
		
		shainExistCheckDto = new ShainExistCheckDto();
		model.addAttribute("shainExistCheckDto", shainExistCheckDto);
		return "shainExistCheck";
	}

	// submit shainExistCheck page
	@RequestMapping(value = "/shainExistCheck", method = RequestMethod.POST)
	public String search(@Valid ShainExistCheckDto emp, BindingResult bindingResult, Model model) {
		
		// check input value is valid or not
		if (bindingResult.hasErrors()) {
			shainExistCheckDto = new ShainExistCheckDto();
			return "shainExistCheck";
		}

		// call API to get data
		if (callAPIx(emp.getEmployeeId()) == 1) { // success

			// FIXME: created dump data for displaying data on GUI
			shainExistCheckDto = getDataFromAPI();
			
			model.addAttribute("employeeInfo", getEmployeeInfo());
			model.addAttribute("shainExistCheckDto", shainExistCheckDto);

		} else { // failed
			
			shainExistCheckDto = new ShainExistCheckDto();
			bindingResult.rejectValue("employeeId", "NotExist.shainExistCheckDto.employeeId");
		}

		return "shainExistCheck";

	}

	@RequestMapping(value = "/next", method = RequestMethod.POST)
	public String next(ShainExistCheckDto emp, BindingResult bindingResult, Model model) {

		if (shainExistCheckDto!= null && shainExistCheckDto.getEmployeeId() != null) {
			return "redirect:/staffExistCheck";
		} else {
			bindingResult.rejectValue("employeeId", "Session.shainExistCheckDto.employeeId");
			shainExistCheckDto = new ShainExistCheckDto();
			return "shainExistCheck";
		}
	}

	/** call API to get data
	 * 
	 * @Input:  employeeId
	 * 
	 * */
	private int callAPIx(String employeeId) {
		if ("123456".equals(employeeId)) { // dummy employeeId
			return 1;
		} 
		return 0;
	}
	
	/** get employee info*/
	private String getEmployeeInfo() {
		String employeeInfo = shainExistCheckDto.getFirstName() + " " + shainExistCheckDto.getLastName() + 
				"(" + shainExistCheckDto.getFirstNameKana() + " " + shainExistCheckDto.getLastNameKana() + ")";
		
		return "お名前: " + employeeInfo;
	}
	
	/** create dummy data */
	private ShainExistCheckDto getDataFromAPI() {
		
		ShainExistCheckDto shainExistCheckDto = new ShainExistCheckDto();
		shainExistCheckDto.setFirstName("Phu");
		shainExistCheckDto.setLastName("Truong");
		shainExistCheckDto.setFirstNameKana("PHU");
		shainExistCheckDto.setLastNameKana("TRUONG");
		shainExistCheckDto.setEmployeeId("123456");
		
		return shainExistCheckDto;
	}
}
