package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.athuman.mynumber.web.dto.StaffInfoDto;
import com.athuman.mynumber.web.dto.StaffInfoResponseDto;
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.service.StaffAPIService;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.StringUtil;
import com.athuman.mynumber.web.util.TokenProcessor;

@Controller
public class StaffExistCheckController {

	@Autowired(required=true)
	@Qualifier(value="staffAPIService")
	private StaffAPIService staffAPIService;

	@RequestMapping(value = MyNumberUrl.STAFF_EXIST_CHECK, method = RequestMethod.GET)
	public String show(Model model, @RequestParam("token") String requestToken) {
	
		model.addAttribute("token", requestToken);
		model.addAttribute("staffInfoModel", new StaffInfoModel());
		return MyNumberJsp.STAFF_EXIST_CHECK;
	}

	// submit staffExistCheck page
	@RequestMapping(value = MyNumberUrl.STAFF_EXIST_CHECK, method = RequestMethod.POST)
	public String search(@RequestBody String staffInfo, 
			@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModelForm, 
			BindingResult bindingResult,
			Model model, 
			HttpSession session, 
			WebRequest request) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		StaffInfoDto staffInfoDtoJson = mapper.readValue(staffInfo, StaffInfoDto.class);

		// Check token
		if (!TokenProcessor.isTokenValid(request, staffInfoDtoJson.getToken())) {
			bindingResult.rejectValue("staffNo", "S00002", new Object [] {}, null);
			return MyNumberJsp.STAFF_EXIST_CHECK;
		}
		TokenProcessor.saveToken(request, model);

		// call API to get data
		// TODO: replace hard-code in returned value in case [readStaff API] is created.
		ResponseEntity<StaffInfoResponseDto> staffInfoResponseDto = staffAPIService.readStaff(staffInfoDtoJson.getStaffNo());

		if (HttpStatus.OK == staffInfoResponseDto.getStatusCode()) { // OK
			StaffInfoDto staffInfoDto = staffInfoResponseDto.getBody().getStaffInfoDto();

			model.addAttribute("staffNo", staffInfoDtoJson.getStaffNo());
			model.addAttribute("staffInfo", getStaffInfo(staffInfoDto));

			// convert data from StaffInfoDto to StaffInfoModel
			StaffInfoModel staffInfoModel = new StaffInfoModel();

			staffInfoModel.setStaffNameSei(staffInfoDto.getNameSei());
			staffInfoModel.setStaffNameMei(staffInfoDto.getNameMei());
			staffInfoModel.setStaffNameSeiKana(staffInfoDto.getNameKanaSei());
			staffInfoModel.setStaffNameMeiKana(staffInfoDto.getNameKanaMei());
			staffInfoModel.setStaffNo(staffInfoDtoJson.getStaffNo());

			// store to session
			session.setAttribute("staffInfoModel", staffInfoModel);

		} else if (HttpStatus.NO_CONTENT == staffInfoResponseDto.getStatusCode()) { // error 204
			session.setAttribute("staffInfoModel", null);
			bindingResult.rejectValue("staffNo", "I00001",
					new Object[] {}, null);
		} else { // other error
			session.setAttribute("staffInfoModel", null);
			bindingResult.rejectValue("staffNo", "S00001",
					new Object[] {"スタッフNo"}, null);
		}

		return MyNumberJsp.STAFF_EXIST_CHECK;
	}

	@RequestMapping(value = MyNumberUrl.NEXT_TO_PURPOSE_CONSENT, method = RequestMethod.POST)
	public String next(@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModelForm,
			BindingResult bindingResult, 
			Model model, 
			HttpSession session, 
			WebRequest request, 
			@RequestParam("token") String requestToken) {

		// Check token
		if (!TokenProcessor.isTokenValid(request, requestToken)) {
			bindingResult.rejectValue("staffNo", "S00002", new Object [] {}, null);
			return MyNumberJsp.STAFF_EXIST_CHECK;
		}
		TokenProcessor.saveToken(request, model);

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
	public String back(@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModelForm,
			BindingResult bindingResult, Model model, WebRequest request, @RequestParam("token") String requestToken) {
		// Check token
		if (!TokenProcessor.isTokenValid(request, requestToken)) {
			bindingResult.rejectValue("staffNo", "S00002", new Object [] {}, null);
			return MyNumberJsp.STAFF_EXIST_CHECK;
		}
		TokenProcessor.saveToken(request, model);
		return MyNumberJsp.REDIRECT_SHAIN_EXIST_CHECK;
	}

	@ResponseBody
	@RequestMapping(value = MyNumberUrl.REMOVE_SESSION_STAFF, method = RequestMethod.POST)
	public String removeSession(HttpSession session) {
		session.setAttribute("staffInfoModel", null);
		return MyNumberJsp.STAFF_EXIST_CHECK;
	}

	/** get staff info*/
	private String getStaffInfo(StaffInfoDto staffInfoDto) {
		String staffInfo = staffInfoDto.getNameSei() + " " + staffInfoDto.getNameMei() +
				"(" + staffInfoDto.getNameKanaSei() + " " + staffInfoDto.getNameKanaMei() + ")";
		return staffInfo;
	}

	public void setStaffAPIService(StaffAPIService staffAPIService) {
		this.staffAPIService = staffAPIService;
	}
}
