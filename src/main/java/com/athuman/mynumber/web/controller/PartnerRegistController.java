package com.athuman.mynumber.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.dto.Dependents;
import com.athuman.mynumber.web.model.DependentsInfoListModel;
import com.athuman.mynumber.web.model.StaffInfoModel;

@Controller
public class PartnerRegistController {

	// inject staffExistCheckDto
//	private DependentsInfoListModel dependentsInfoList;


	// show myNumberRegist page
	@RequestMapping(value = "/partnerRegist", method = RequestMethod.GET)
	public String show(Model model) {

		DependentsInfoListModel dependentsInfoListModel = new DependentsInfoListModel();
		List<Dependents> lstDependents = new ArrayList<Dependents>();
		for (int i = 0; i < 9; i++) {
			lstDependents.add(new Dependents());
		}
		dependentsInfoListModel.setDependents(lstDependents);
		model.addAttribute("lstDependentsInfo", dependentsInfoListModel);
		return "partnerRegist";
	}

	// submit myNumberRegist page
	@RequestMapping(value = "/partnerRegist", method = RequestMethod.POST)
	public String doPartnerRegistNext(
			@ModelAttribute("lstDependentsInfo")DependentsInfoListModel lstDependentsInfo,
			BindingResult binding, Model model, HttpSession session) {

		StaffInfoModel staffInfoModel = (StaffInfoModel)session.getAttribute("staffInfoModel");
		List<Dependents> listdDependents = lstDependentsInfo.getDependents();
		for (int i = 0; i < listdDependents.size(); i++) {
			if (!listdDependents.get(i).getDependentsNameSei().equals(staffInfoModel.getStaffName())) {
				binding.rejectValue("dependents",
						"Test.dependentsInfoListModel.deference",
						new Object[] {"扶養者" + (i+1)},
						"dependents" );
			}
		}
		return "partnerRegist";
	}

	@RequestMapping(value = "/partnerRegistBack", method = RequestMethod.POST)
	public String doPartnerRegistBack(Model model) {

		return "redirect:/myNumberRegist";
	}

}
