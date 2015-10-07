package com.athuman.mynumber.web.controller;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
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
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.PropertyUtil;
import com.athuman.mynumber.web.util.StringUtil;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
public class StaffExistCheckController {

	@RequestMapping(value = MyNumberUrl.STAFF_EXIST_CHECK, method = RequestMethod.GET)
	public String show(Model model, @RequestParam("token") String requestToken) {
	
		model.addAttribute("token", requestToken);
		model.addAttribute("staffInfoModel", new StaffInfoModel());
		model.addAttribute("staffExistCheckApi", PropertyUtil.getProperties("application.properties","tact.api.staff.url"));
		return MyNumberJsp.STAFF_EXIST_CHECK;
	}

	// submit staffExistCheck page
	@RequestMapping(value = MyNumberUrl.SEARCH_STAFF, method = RequestMethod.POST)
	public String search(@RequestBody String staffInfo, 
			@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModelForm, 
			BindingResult binding,
			Model model, 
			HttpSession session, 
			WebRequest request) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		StaffInfoDto staffInfoDto = mapper.readValue(staffInfo, StaffInfoDto.class);

		// Check token
		if (!ValidateUtil.isValidToken("staffNo",
										request,
										staffInfoDto.getToken(), 
										binding, model)) {

			return MyNumberJsp.STAFF_EXIST_CHECK;
		}

		model.addAttribute("staffNo", staffInfoDto.getStaffNo());
		model.addAttribute("staffInfo", getStaffInfo(staffInfoDto));

		// convert data from StaffInfoDto to StaffInfoModel
		StaffInfoModel staffInfoModel = new StaffInfoModel();

		staffInfoModel.setStaffNameSei(staffInfoDto.getNameSei());
		staffInfoModel.setStaffNameMei(staffInfoDto.getNameMei());
		staffInfoModel.setStaffNameSeiKana(staffInfoDto.getNameKanaSei());
		staffInfoModel.setStaffNameMeiKana(staffInfoDto.getNameKanaMei());
		staffInfoModel.setStaffNo(staffInfoDto.getStaffNo());

		// store to session
		session.setAttribute("staffInfoModel", staffInfoModel);

		return MyNumberJsp.STAFF_EXIST_CHECK;
	}

	@RequestMapping(value = MyNumberUrl.STAFF_EXIST_CHECK, method = RequestMethod.POST)
	public String next(@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModelForm,
			BindingResult binding, 
			Model model, 
			HttpSession session, 
			WebRequest request, 
			@RequestParam("token") String requestToken) {

		// Check token
		if (!ValidateUtil.isValidToken("staffNo", request, requestToken, binding, model)) {
			return MyNumberJsp.STAFF_EXIST_CHECK;
		}

		StaffInfoModel staffInfoModel = (StaffInfoModel)session.getAttribute("staffInfoModel");

		if (staffInfoModel!= null &&
				StringUtil.isNotEmpty(staffInfoModel.getStaffNo())) {

			return MyNumberJsp.REDIRECT_PURPOSE_CONSENT;
		} else {
			binding.rejectValue("staffNo", "V00001", new Object [] {"スタッフNo"}, null);
			return MyNumberJsp.STAFF_EXIST_CHECK;
		}
	}

	@RequestMapping(value = MyNumberUrl.BACK_TO_SHAIN_EXIST_CHECK, method = RequestMethod.POST)
	public String back(@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModelForm,
			BindingResult binding, Model model, WebRequest request, @RequestParam("token") String requestToken) {
		
		// Check token
		if (!ValidateUtil.isValidToken("staffNo", request, requestToken, binding, model)) {
			return MyNumberJsp.STAFF_EXIST_CHECK;
		}
		
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
}
