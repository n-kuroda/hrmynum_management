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

import com.athuman.mynumber.web.dto.ShainInfoDto;
import com.athuman.mynumber.web.model.ShainInfoModel;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.StringUtil;
import com.athuman.mynumber.web.util.TokenProcessor;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
public class ShainExistCheckController {

	// display shainExistCheck page
	@RequestMapping(value = MyNumberUrl.SHAIN_EXIST_CHECK, method = RequestMethod.GET)
	public String show(WebRequest request, Model model, HttpSession session) {

		// clear all session
		session.invalidate();

		TokenProcessor.saveToken(request, model);
		model.addAttribute("shainInfoModel", new ShainInfoModel());
		return MyNumberJsp.SHAIN_EXIST_CHECK;
	}

	// submit shainExistCheck page
	@RequestMapping(value = MyNumberUrl.SHAIN_EXIST_CHECK, method = RequestMethod.POST)
	public String search(@RequestBody String shainInfo, 
			@ModelAttribute("shainInfoModel") ShainInfoModel shainInfoModelForm, 
			BindingResult binding,
			Model model, 
			HttpSession session,
			WebRequest request) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		ShainInfoDto shainInfoDto = mapper.readValue(shainInfo, ShainInfoDto.class);

		// Check token
		if (!ValidateUtil.isValidToken("shainNo",
										request, 
										shainInfoDto.getToken(), 
										binding, model)) {

			return MyNumberJsp.SHAIN_EXIST_CHECK;
		}

		model.addAttribute("shainNo", shainInfoDto.getShainNo());
		model.addAttribute("shainInfo", getShainInfo(shainInfoDto));

		// convert data from dto to model and store to session
		ShainInfoModel shainInfoModel = new ShainInfoModel();

		shainInfoModel.setShainNameMei(shainInfoDto.getShainNameMei());
		shainInfoModel.setShainNameMeiKana(shainInfoDto.getShainNameMeiKana());
		shainInfoModel.setShainNameSei(shainInfoDto.getShainNameSei());
		shainInfoModel.setShainNameSeiKana(shainInfoDto.getShainNameSeiKana());
		shainInfoModel.setShainNo(shainInfoDto.getShainNo());

		// store to session
		session.setAttribute("shainInfoModel", shainInfoModel);

		return MyNumberJsp.SHAIN_EXIST_CHECK;

	}

	@RequestMapping(value = MyNumberUrl.NEXT_TO_STAFF_EXIST_CHECK, method = RequestMethod.POST)
	public String next(@ModelAttribute("shainInfoModel") ShainInfoModel shainInfoModelForm,
			BindingResult binding,
			Model model,
			HttpSession session, 
			WebRequest request, 
			@RequestParam("token") String requestToken) {

		// Check token
		if (!ValidateUtil.isValidToken("shainNo", request, requestToken, binding, model)) {
			return MyNumberJsp.SHAIN_EXIST_CHECK;
		}

		ShainInfoModel shainInfoModel = (ShainInfoModel)session.getAttribute("shainInfoModel");

		if (shainInfoModel != null &&
				StringUtil.isNotEmpty(shainInfoModel.getShainNo())) {

			return MyNumberJsp.REDIRECT_STAFF_EXIST_CHECK;
		} else {
			binding.rejectValue("shainNo", "V00001", new Object [] {"社員番号"}, null);
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

	@ResponseBody
	@RequestMapping(value = MyNumberUrl.REMOVE_SESSION_SHAIN, method = RequestMethod.POST)
	public String removeSession(HttpSession session) {
		session.setAttribute("shainInfoModel", null);
		return MyNumberJsp.SHAIN_EXIST_CHECK;
	}
}
