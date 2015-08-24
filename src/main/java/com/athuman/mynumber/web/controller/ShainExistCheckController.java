package com.athuman.mynumber.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.athuman.mynumber.web.dto.ShainExistCheckDto;

@Controller
public class ShainExistCheckController {

	// inject shainExistCheckDto
	private ShainExistCheckDto shainExistCheckDto;

	// display shainExistCheck page
	@RequestMapping(value = "/shainExistCheck", method = RequestMethod.GET)
	public String show(Model model) {
		
		// check whether session is initial or not
		if (shainExistCheckDto == null || shainExistCheckDto.getEmployeeId() == null) {
			shainExistCheckDto = new ShainExistCheckDto();
		} else {
			String employeeInfo = shainExistCheckDto.getFirstName() + " " + shainExistCheckDto.getLastName() + 
					"(" + shainExistCheckDto.getFirstNameKana() + " " + shainExistCheckDto.getLastNameKana() + ")";
			
			model.addAttribute("employeeInfo", employeeInfo);
		}
		
		model.addAttribute("shainExistCheckDto", shainExistCheckDto);
		return "shainExistCheck";
	}

	// submit shainExistCheck page
	@RequestMapping(value = "/shainExistCheck", method = RequestMethod.POST)
	public String search(@RequestParam String action, ShainExistCheckDto emp, BindingResult bindingResult, Model model) {
		
		// detect the action is [search] or not
		if ("search".equals(action)) {
			
			// check input value is valid or not
			if (bindingResult.hasErrors()) {
				shainExistCheckDto = new ShainExistCheckDto();
				return "shainExistCheck";
			}
			
			// call API to get data
			if (callAPIx(emp.getEmployeeId()) == 1) { // success

				// FIXME: created dump data for displaying data on GUI
				shainExistCheckDto = getDataFromAPI();
				
				String employeeInfo = shainExistCheckDto.getFirstName() + " " + shainExistCheckDto.getLastName() + 
						"(" + shainExistCheckDto.getFirstNameKana() + " " + shainExistCheckDto.getLastNameKana() + ")";
				
				model.addAttribute("employeeInfo", employeeInfo);
				model.addAttribute("shainExistCheckDto", shainExistCheckDto);

			} else { // failed
				bindingResult.rejectValue("employeeId", "NotExist.shainExistCheckDto.employeeId");
				shainExistCheckDto = new ShainExistCheckDto();
			}

			return "shainExistCheck";
			
		} else { // [next] btn clicked
			
			// check whether employeeId has stored to session or not
			if (shainExistCheckDto!= null && shainExistCheckDto.getEmployeeId() != null) {
				return "redirect:/staffExistCheck";
			} else {
				
				bindingResult.rejectValue("employeeId", "Session.shainExistCheckDto.employeeId");
				shainExistCheckDto = new ShainExistCheckDto();
				return "shainExistCheck";
			}
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
