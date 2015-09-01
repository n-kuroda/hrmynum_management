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
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.StringUtil;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
@SessionAttributes("shainInfoModel")
public class ShainExistCheckController {

	@Autowired(required=true)
	@Qualifier(value="myNumberService")
	private MyNumberService myNumberService;
	
	// display shainExistCheck page
	@RequestMapping(value = MyNumberUrl.SHAIN_EXIST_CHECK, method = RequestMethod.GET)
	public String show(Model model) {
		
		model.addAttribute("shainInfoDto", new ShainInfoDto());
		return MyNumberJsp.SHAIN_EXIST_CHECK;
	}

	// submit shainExistCheck page
	@RequestMapping(value = MyNumberUrl.SHAIN_EXIST_CHECK, method = RequestMethod.POST)
	public String search(ShainInfoDto shainInfoDtoForm, BindingResult bindingResult, Model model) {
		
		// check input value is valid or not
		if (ValidateUtil.checkInputValid("shainNo", "社員番号", shainInfoDtoForm.getShainNo(), bindingResult, 6).hasErrors()) {
			model.addAttribute("shainInfoModel", new ShainInfoModel());
			return MyNumberJsp.SHAIN_EXIST_CHECK;
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
					new Object[] {"社員番号"}, null);
		}

		return MyNumberJsp.SHAIN_EXIST_CHECK;

	}

	@RequestMapping(value = MyNumberUrl.NEXT_TO_STAFF_EXIST_CHECK, method = RequestMethod.POST)
	public String next(ShainInfoDto shainInfoDtoForm, BindingResult bindingResult, Model model, HttpSession session) {

		ShainInfoModel shainInfoModel = (ShainInfoModel)session.getAttribute("shainInfoModel");
		
		if (shainInfoModel != null && 
				StringUtil.isNotEmpty(shainInfoModel.getShainNo())) {
			
			return MyNumberJsp.REDIRECT_STAFF_EXIST_CHECK;
		} else {
			bindingResult.rejectValue("shainNo", "V00001", new Object [] {"社員番号"}, null);
			shainInfoModel = new ShainInfoModel();
			return MyNumberJsp.SHAIN_EXIST_CHECK;
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
