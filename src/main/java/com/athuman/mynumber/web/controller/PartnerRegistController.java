package com.athuman.mynumber.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PartnerRegistController {

	// inject staffExistCheckDto
//	private DependentsInfoListModel dependentsInfoList;


	// show myNumberRegist page
//	@RequestMapping(value = "/partnerRegist", method = RequestMethod.GET)
//	public String show(Model model) {
//
//		if (dependentsInfoList != null) {
//			model.addAttribute("dependentsInfoList", dependentsInfoList);
//		} else {
//			dependentsInfoList = new DependentsInfoListModel();
//			model.addAttribute("dependentsInfoList", dependentsInfoList);
//		}
//
//		return "myNumberRegist";
//	}

	// submit myNumberRegist page
//	@RequestMapping(value = "/partnerRegist", method = RequestMethod.POST)
//	public String doPartnerRegistNext(@Valid DependentsInfoListModel lstDependentsInfo, BindingResult binding, Model model) {

//		if (binding.hasErrors()) { // when form has error
//			return "myNumberRegist";
//		} else { // success
//
//			// TODO store data to session
//			myNumberRegistDto = processData(myNumber);
//
//			// check at last one check box selected
//			if (checkSelect(myNumberRegistDto)) {
//				binding.rejectValue("other","Verification.myNumberRegistDto.verification");
//				return "myNumberRegist";
//			}
//
//		}
//		return "partnerRegist";
//	}

	@RequestMapping(value = "/partnerRegistBack", method = RequestMethod.POST)
	public String doPartnerRegistBack(Model model) {

		return "redirect:/myNumberRegist";
	}

}
