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

import com.athuman.mynumber.web.dto.ShainInfoDto;
import com.athuman.mynumber.web.dto.ShainInfoResponseDto;
import com.athuman.mynumber.web.model.ShainInfoModel;
import com.athuman.mynumber.web.service.MyNumberService;
import com.athuman.mynumber.web.util.StringUtil;
import com.athuman.mynumber.web.util.ValidateUtil;

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
	public String search(ShainInfoDto shainInfoDtoForm, BindingResult bindingResult, Model model) {
		
		// check input value is valid or not
		if (ValidateUtil.checkInputValid("shainNo", shainInfoDtoForm.getShainNo(), bindingResult, 6).hasErrors()) {
			model.addAttribute("shainInfoModel", new ShainInfoModel());
			return "shainExistCheck";
		}

		// call API to get data
		// FIXME: created dump data for displaying data on GUI
		ShainInfoResponseDto shainInfoResponseDto = myNumberService.readShain(shainInfoDtoForm.getShainNo());
			
		if (shainInfoResponseDto.getHttpStatus() == 200) { // OK
			ShainInfoDto shainInfoDto = shainInfoResponseDto.getShainInfoDto();

			model.addAttribute("employeeNo", shainInfoDtoForm.getShainNo());
			model.addAttribute("shainInfo", getShainInfo(shainInfoDto));

			// convert data from dto to model and store to session
			ShainInfoModel shainInfoModel = new ShainInfoModel();

			shainInfoModel.setShainNameMei(shainInfoDto.getShainNameMei());
			shainInfoModel.setShainNameMeiKana(shainInfoDto.getShainNameMeiKana());
			shainInfoModel.setShainNameSei(shainInfoDto.getShainNameSei());
			shainInfoModel.setShainNameSeiKana(shainInfoDto.getShainNameSeiKana());
			shainInfoModel.setShainNo(shainInfoDto.getShainNo());
			// store to session
			model.addAttribute("shainInfoModel", shainInfoModel);

		} else if (shainInfoResponseDto.getHttpStatus() == 204) { // error 204

			model.addAttribute("shainInfoModel", new ShainInfoModel());
			bindingResult.rejectValue("shainNo", "I00001",
					new Object[] {}, null);
		} else { // other error
			model.addAttribute("shainInfoModel", new ShainInfoModel());
			bindingResult.rejectValue("shainNo", "S00001",
					new Object[] { shainInfoDtoForm.getShainNo() }, null);
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
			bindingResult.rejectValue("shainNo", "V00001", new Object [] {"shainNo"}, null);
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
