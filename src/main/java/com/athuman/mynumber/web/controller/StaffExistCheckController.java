package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athuman.mynumber.web.dto.StaffInfoDto;
import com.athuman.mynumber.web.dto.StaffInfoResponseDto;
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.service.StaffAPIService;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.StringUtil;

@Controller
public class StaffExistCheckController {

	@Autowired(required=true)
	@Qualifier(value="staffAPIService")
	private StaffAPIService staffAPIService;

	@RequestMapping(value = MyNumberUrl.STAFF_EXIST_CHECK, method = RequestMethod.GET)
	public String show(Model model) {

		model.addAttribute("staffInfoModel", new StaffInfoModel());
		return MyNumberJsp.STAFF_EXIST_CHECK;
	}

	// submit staffExistCheck page
	@RequestMapping(value = MyNumberUrl.STAFF_EXIST_CHECK, method = RequestMethod.POST)
	@ResponseBody
	public StaffInfoResponseDto search(@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModelForm,
			BindingResult bindingResult, Model model, 
			@RequestBody String staffNo, HttpSession session) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		staffInfoModelForm = mapper.readValue(staffNo, StaffInfoModel.class);
		
		// call API to get data
		// FIXME: created dump data for displaying data on GUI
		StaffInfoResponseDto staffInfoResponseDto = staffAPIService.readStaff(staffInfoModelForm.getStaffNo());

		if (staffInfoResponseDto.getHttpStatus() == 200) { // OK

			StaffInfoDto staffInfoDto = staffInfoResponseDto.getStaffInfoDto();

			// convert data from StaffInfoDto to StaffInfoModel
			StaffInfoModel staffInfoModel = new StaffInfoModel();

			staffInfoModel.setStaffNameSei(staffInfoDto.getNameSei());
			staffInfoModel.setStaffNameMei(staffInfoDto.getNameMei());
			staffInfoModel.setStaffNameSeiKana(staffInfoDto.getNameKanaSei());
			staffInfoModel.setStaffNameMeiKana(staffInfoDto.getNameKanaMei());
			staffInfoModel.setStaffNo(staffInfoModelForm.getStaffNo());

			// store to session
			session.setAttribute("staffInfoModel", staffInfoModel);

		} else { // other error
			session.setAttribute("staffInfoModel", null);
		}

		return staffInfoResponseDto;
	}

	@RequestMapping(value = MyNumberUrl.NEXT_TO_PURPOSE_CONSENT, method = RequestMethod.POST)
	public String next(@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModelForm,
			BindingResult bindingResult, Model model, HttpSession session) {

		StaffInfoModel staffInfoModel = (StaffInfoModel)session.getAttribute("staffInfoModel");

		if (staffInfoModel!= null &&
				StringUtil.isNotEmpty(staffInfoModel.getStaffNo())) {

			return MyNumberJsp.REDIRECT_PURPOSE_CONSENT;
		} else {
			bindingResult.rejectValue("staffNo", "V00001", new Object [] {"スタッフNo"}, null);
			return MyNumberJsp.STAFF_EXIST_CHECK;
		}
	}

	@RequestMapping(value = MyNumberUrl.BACK_TO_SHAIN_EXIST_CHECK, method = RequestMethod.POST)
	public String back(Model model) {
		return MyNumberJsp.REDIRECT_SHAIN_EXIST_CHECK;
	}

	public void setStaffAPIService(StaffAPIService staffAPIService) {
		this.staffAPIService = staffAPIService;
	}
}
