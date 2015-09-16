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

import com.athuman.mynumber.web.dto.ShainInfoDto;
import com.athuman.mynumber.web.dto.ShainInfoResponseDto;
import com.athuman.mynumber.web.model.ShainInfoModel;
import com.athuman.mynumber.web.service.ShainAPIService;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.StringUtil;

@Controller
public class ShainExistCheckController {

	@Autowired(required=true)
	@Qualifier(value="shainAPIService")
	private ShainAPIService shainAPIService;

	// display shainExistCheck page
	@RequestMapping(value = MyNumberUrl.SHAIN_EXIST_CHECK, method = RequestMethod.GET)
	public String show(Model model) {

		model.addAttribute("shainInfoModel", new ShainInfoModel());
		return MyNumberJsp.SHAIN_EXIST_CHECK;
	}

	// submit shainExistCheck page
	@ResponseBody
	@RequestMapping(value = MyNumberUrl.SHAIN_EXIST_CHECK, method = RequestMethod.POST)
	public ShainInfoResponseDto search(@ModelAttribute("shainInfoModel") ShainInfoModel shainInfoModelForm,
			BindingResult bindingResult, Model model,
			@RequestBody String shainNo, HttpSession session) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		shainInfoModelForm = mapper.readValue(shainNo, ShainInfoModel.class);

		// call API to get data
		// FIXME: created dump data for displaying data on GUI
		ShainInfoResponseDto shainInfoResponseDto = shainAPIService.readShain(shainInfoModelForm.getShainNo());

		if (shainInfoResponseDto.getHttpStatus() == 200) { // OK
			ShainInfoDto shainInfoDto = shainInfoResponseDto.getShainInfoDto();

			// convert data from dto to model and store to session
			ShainInfoModel shainInfoModel = new ShainInfoModel();

			shainInfoModel.setShainNameMei(shainInfoDto.getShainNameMei());
			shainInfoModel.setShainNameMeiKana(shainInfoDto.getShainNameMeiKana());
			shainInfoModel.setShainNameSei(shainInfoDto.getShainNameSei());
			shainInfoModel.setShainNameSeiKana(shainInfoDto.getShainNameSeiKana());
			shainInfoModel.setShainNo(shainInfoDto.getShainNo());

			// store to session
			session.setAttribute("shainInfoModel", shainInfoModel);

		} else { // other error
			session.setAttribute("shainInfoModel", null);
		}

		return shainInfoResponseDto;

	}

	@RequestMapping(value = MyNumberUrl.NEXT_TO_STAFF_EXIST_CHECK, method = RequestMethod.POST)
	public String next(@ModelAttribute("shainInfoModel") ShainInfoModel shainInfoModelForm,
			BindingResult bindingResult, Model model, HttpSession session) {

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

	public void setShainAPIService(ShainAPIService shainAPIService) {
		this.shainAPIService = shainAPIService;
	}

}
