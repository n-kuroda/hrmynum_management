package com.athuman.mynumber.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
public class PartnerRegistController {

	// show partnerRegist page
	@RequestMapping(value = MyNumberUrl.PARTNER_REGIST, method = RequestMethod.GET)
	public String show(Model model, HttpSession session) {

		DependentsInfoListModel lstDependents = (DependentsInfoListModel)session.getAttribute("dependentsInfoListModel");
		if (lstDependents == null ||
				lstDependents.getDependents() == null) {

			StaffInfoModel staffSession = (StaffInfoModel)session.getAttribute("staffInfoModel");
			lstDependents = initDataForm(staffSession);
		}
		initModelList(model);
		model.addAttribute("dependentsInfoListModel", lstDependents);
		return MyNumberJsp.PARTNER_REGIST;
	}

	// submit partnerRegist page
	@RequestMapping(value = MyNumberUrl.PARTNER_REGIST, method = RequestMethod.POST)
	public String next(
			@ModelAttribute("dependentsInfoListModel")DependentsInfoListModel lstDependentsInfo,
			BindingResult binding, Model model, HttpSession session) {

		// get staff name for validate
		StaffInfoModel staffInfoModel = (StaffInfoModel)session.getAttribute("staffInfoModel");

		// begin valid each form partner
		List<Dependents> listdDependents = lstDependentsInfo.getDependents();
		for (int i = 0; i < listdDependents.size(); i++) {
			if (listdDependents.get(i).getNo3Insured() == null) {
				listdDependents.get(i).setNo3Insured("0");
			}
			binding = ValidateUtil.validFormPartner(binding, listdDependents.get(i), staffInfoModel, i);
		}

		// when form has error
		if (binding.hasErrors()) {
			initModelList(model);
			return MyNumberJsp.PARTNER_REGIST;
		}

		// when form don't has error store session
		storeSession(model, lstDependentsInfo, staffInfoModel, session);
		return MyNumberJsp.REDIRECT_STAFF_SIGNING;
	}

	@RequestMapping(value = MyNumberUrl.BACK_TO_MYNUMBER_REGIST, method = RequestMethod.POST)
	public String back(Model model,
			@ModelAttribute("dependentsInfoListModel")DependentsInfoListModel lstDependentsInfo,
			HttpSession session) {

		// get staff info form session
		StaffInfoModel staffSession = (StaffInfoModel)session.getAttribute("staffInfoModel");
		// store session
		storeSession(model, lstDependentsInfo, staffSession, session);
		return MyNumberJsp.REDIRECT_MYNUMBER_REGIST;
	}

	/** Store DependentsInfoList to session
	 *
	 * @param model
	 * @param lstDependentsInfo
	 * @param staffSession
	 */
	private void storeSession(Model model, DependentsInfoListModel lstDependentsInfo,
			StaffInfoModel staffSession, HttpSession session){

		// init swap index
		int swapIndex = 0;

		// begin swap item has change to top
		DependentsInfoListModel dependentsInfoListModel = new DependentsInfoListModel();
		List<Dependents> listdDependents = lstDependentsInfo.getDependents();
		for (int i = 0; i < listdDependents.size(); i++) {
			if (ValidateUtil.checkPartnerHasChange(listdDependents.get(i), staffSession) ||
					ValidateUtil.checkPartnerFormHasEdit(listdDependents.get(i))) {
				Collections.swap(listdDependents, i, swapIndex);
				swapIndex++;
			}
		}
		// store to session
		dependentsInfoListModel.setDependents(listdDependents);
		session.setAttribute("dependentsInfoListModel", dependentsInfoListModel);
	}

	/** Load default value for 10 form partnerRegist
	 *
	 * @param staffInfoModel
	 * @return DependentsInfoListModel
	 */
	private DependentsInfoListModel initDataForm(StaffInfoModel staffInfoModel) {

		DependentsInfoListModel dependentsInfoListModel = new DependentsInfoListModel();
		List<Dependents> lstDependents = new ArrayList<Dependents>();
		Dependents dependents = new Dependents();
		dependents.setDependentsNameSei(staffInfoModel.getStaffNameSei());
		for (int i = 0; i < 10; i++) {
			lstDependents.add(dependents);
		}
		dependentsInfoListModel.setDependents(lstDependents);
		return dependentsInfoListModel;
	}

	/** Init data for select box
	 *
	 * @param model
	 */
	private void initModelList(Model model) {

		// init list year
		int curYear = Calendar.getInstance().get(Calendar.YEAR);
		List<String> lstYear = new ArrayList<String>();
		for (int i = 1990; i <= curYear; i++) {
			lstYear.add("" + i);
		}
		model.addAttribute("listYear", lstYear);

		List<String> lstMonth = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			lstMonth.add("" + i);
		}
		model.addAttribute("listMonth", lstMonth);

		List<String> lstDay = new ArrayList<String>();
		for (int i = 1; i <= 31; i++) {
			lstDay.add("" + i);
		}
		model.addAttribute("listDay", lstDay);

		Map<String,String> relationship = new LinkedHashMap<String,String>();
		relationship.put("01", "妻");
		relationship.put("02", "夫");
		relationship.put("03", "子供");
		relationship.put("04", "父");
		relationship.put("05", "母");
		relationship.put("06", "兄弟・姉妹");
		relationship.put("07", "その他");
		model.addAttribute("listRelationship", relationship);

	}

}
