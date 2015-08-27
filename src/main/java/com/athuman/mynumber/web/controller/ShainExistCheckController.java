package com.athuman.mynumber.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.dto.ShainExistCheckDto;
import com.athuman.mynumber.web.service.MyNumberService;

@Controller
public class ShainExistCheckController {
	
	// inject shainExistCheckDto
	private ShainExistCheckDto shainExistCheckDto;
	
	private MyNumberService myNumberService;
	
	@Autowired(required=true)
	@Qualifier(value="myNumberService")
	public void setMyNumberService(MyNumberService myNumberService) {
		this.myNumberService = myNumberService;
	}

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
		// FIXME: created dump data for displaying data on GUI
		shainExistCheckDto = myNumberService.readShain(emp.getEmployeeId());
		
		if (shainExistCheckDto != null) { // success

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
	
	/** get employee info*/
	private String getEmployeeInfo() {
		String employeeInfo = shainExistCheckDto.getFirstName() + " " + shainExistCheckDto.getLastName() + 
				"(" + shainExistCheckDto.getFirstNameKana() + " " + shainExistCheckDto.getLastNameKana() + ")";
		
		return employeeInfo;
	}
}
