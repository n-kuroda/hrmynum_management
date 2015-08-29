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

import com.athuman.mynumber.web.dto.ShainInfoDto;
import com.athuman.mynumber.web.model.ShainInfoModel;
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
		
		model.addAttribute("shainInfoDto", new ShainInfoDto());
		return "shainExistCheck";
	}

	// submit shainExistCheck page
	@RequestMapping(value = "/shainExistCheck", method = RequestMethod.POST)
	public String search(@Valid ShainInfoDto shainInfoDtoForm, BindingResult bindingResult, Model model) {
		
		// check input value is valid or not
		if (bindingResult.hasErrors()) {
			model.addAttribute("shainInfoModel", new ShainInfoModel());
			return "shainExistCheck";
		}

		// call API to get data
		// FIXME: created dump data for displaying data on GUI
		ShainInfoDto shainInfoDto = myNumberService.readShain(shainInfoDtoForm.getShainNo());
		
		if (shainInfoDto != null) { // success
			
			model.addAttribute("shainInfo", getShainInfo(shainInfoDto));
			
			// convert data from dto to model and store to session
			ShainInfoModel shainInfoModel = new ShainInfoModel();
			
			shainInfoModel.setShainNameMei(shainInfoDto.getShainNameMei());
			shainInfoModel.setShainNameMeiKana(shainInfoDto.getShainNameMeiKana());
			shainInfoModel.setShainNameSei(shainInfoDto.getShainNameSei());
			shainInfoModel.setShainNameSeiKana(shainInfoDto.getShainNameSeiKana());
			shainInfoModel.setShainNo(shainInfoDto.getShainNo());
			
			model.addAttribute("shainInfoModel", shainInfoModel);

		} else { // failed
			
			model.addAttribute("shainInfoModel", new ShainInfoModel());
			bindingResult.rejectValue("shainNo", "NotExist.shainInfoDto.shainNo");
		}

		return "shainExistCheck";

	}

	@RequestMapping(value = "/next", method = RequestMethod.POST)
	public String next(ShainInfoDto shainInfoDtoForm, BindingResult bindingResult, Model model, HttpSession session) {

		ShainInfoModel shainInfoModel = (ShainInfoModel)session.getAttribute("shainInfoModel");
		
		if (shainInfoModel != null && 
				StringUtil.isNotEmpty(shainInfoModel.getShainNo())) {
			
			return "redirect:/staffExistCheck";
		} else {
			bindingResult.rejectValue("shainNo", "Session.shainInfoDto.shainNo");
			shainInfoModel = new ShainInfoModel();
			return "shainExistCheck";
		}
	}
	
	/** get Shain info*/
	private String getShainInfo(ShainInfoDto shainInfoDto) {
		String shainInfo = shainInfoDto.getShainNameSei() + " " + shainInfoDto.getShainNameMei() + 
				"(" + shainInfoDto.getShainNameSeiKana() + " " + shainInfoDto.getShainNameMeiKana() + ")";
		
		return shainInfo;
	}

	public void setMyNumberService(MyNumberService myNumberService) {
		this.myNumberService = myNumberService;
	}
}
