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

import com.athuman.mynumber.web.dto.ShainInfoModel;
import com.athuman.mynumber.web.service.MyNumberService;
import com.athuman.mynumber.web.util.StringUtil;

@Controller
@SessionAttributes("shainInfoModel")
public class ShainExistCheckController {

	@Autowired(required=true)
	@Qualifier(value="myNumberService")
	private MyNumberService myNumberService;
	
	// display shainExistCheck page
	@RequestMapping(value = "/shainExistCheck", method = RequestMethod.GET)
	public String show(Model model) {
		
		ShainInfoModel shainInfoModel = new ShainInfoModel();
		model.addAttribute("shainInfoModel", shainInfoModel);
		return "shainExistCheck";
	}

	// submit shainExistCheck page
	@RequestMapping(value = "/shainExistCheck", method = RequestMethod.POST)
	public String search(@Valid ShainInfoModel shainInfoModel, BindingResult bindingResult, Model model) {
		
		// check input value is valid or not
		if (bindingResult.hasErrors()) {
			shainInfoModel = new ShainInfoModel();
			return "shainExistCheck";
		}

		// call API to get data
		// FIXME: created dump data for displaying data on GUI
		shainInfoModel = myNumberService.readShain(shainInfoModel.getShainNo());
		
		if (shainInfoModel != null) { // success
			
			model.addAttribute("employeeInfo", getEmployeeInfo(shainInfoModel));
			model.addAttribute("shainInfoModel", shainInfoModel);

		} else { // failed
			
			shainInfoModel = new ShainInfoModel();
			bindingResult.rejectValue("shainNo", "NotExist.shainInfoModel.shainNo");
		}

		return "shainExistCheck";

	}

	@RequestMapping(value = "/next", method = RequestMethod.POST)
	public String next(ShainInfoModel shainInfoModel, BindingResult bindingResult, Model model) {

		if (shainInfoModel != null && 
				StringUtil.isNotEmpty(shainInfoModel.getShainNo())) {
			
			return "redirect:/staffExistCheck";
		} else {
			bindingResult.rejectValue("shainNo", "Session.shainInfoModel.shainNo");
			shainInfoModel = new ShainInfoModel();
			return "shainExistCheck";
		}
	}
	
	/** get employee info*/
	private String getEmployeeInfo(ShainInfoModel shainInfoModel) {
		String employeeInfo = shainInfoModel.getShainNameSei() + " " + shainInfoModel.getShainNameMei() + 
				"(" + shainInfoModel.getShainNameSeiKana() + " " + shainInfoModel.getShainNameMeiKana() + ")";
		
		return employeeInfo;
	}

	public void setMyNumberService(MyNumberService myNumberService) {
		this.myNumberService = myNumberService;
	}
}
