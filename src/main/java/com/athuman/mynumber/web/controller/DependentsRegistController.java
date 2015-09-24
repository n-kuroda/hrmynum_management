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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.athuman.mynumber.web.dto.Dependents;
import com.athuman.mynumber.web.model.DependentsInfoListModel;
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.util.ConstValues;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
public class DependentsRegistController {

	private static final int NUMBER_OF_DEPENDENT = 10;

	// show dependentsRegist page
	@RequestMapping(value = MyNumberUrl.DEPENDENTS_REGIST, method = RequestMethod.GET)
	public String show(Model model, HttpSession session, @RequestParam("token") String requestToken) {

		model.addAttribute("token", requestToken);
		DependentsInfoListModel lstDependents = (DependentsInfoListModel)session.getAttribute("dependentsInfoListModel");
		if (lstDependents == null ||
				lstDependents.getDependents() == null) {
			
			StaffInfoModel staffSession = (StaffInfoModel)session.getAttribute("staffInfoModel");
			lstDependents = initDataForm(staffSession);
		}
		initModelList(model);
		model.addAttribute("dependentsInfoListModel", lstDependents);
		return MyNumberJsp.DEPENDENTS_REGIST;
	}

	// submit dependentsRegist page
	@RequestMapping(value = MyNumberUrl.DEPENDENTS_REGIST, method = RequestMethod.POST)
	public String next(
			@ModelAttribute("dependentsInfoListModel")DependentsInfoListModel lstDependentsInfo,
			BindingResult binding, Model model, HttpSession session,
			WebRequest request, @RequestParam("token") String requestToken) {

		// Check token
		if (!ValidateUtil.isValidToken("", request, requestToken, binding, model)) {
			initModelList(model);
			return MyNumberJsp.DEPENDENTS_REGIST;
		}
				
		// get staff name for validate
		StaffInfoModel staffInfoModel = (StaffInfoModel)session.getAttribute("staffInfoModel");

		// begin valid each form Dependents
		List<Dependents> listdDependents = lstDependentsInfo.getDependents();
		for (int i = 0; i < listdDependents.size(); i++) {
			if (listdDependents.get(i).getNo3Insured() == null) {
				listdDependents.get(i).setNo3Insured(ConstValues.NO3INSURED);
			}
			binding = ValidateUtil.validFormDependents(binding, listdDependents.get(i), staffInfoModel, i);
		}

		// when form has error
		if (binding.hasErrors()) {
			initModelList(model);
			return MyNumberJsp.DEPENDENTS_REGIST;
		}

		// when form don't has error store session
		storeSession(model, lstDependentsInfo, staffInfoModel, session);
		return MyNumberJsp.REDIRECT_STAFF_REGIST_CONFIRM;
	}

	@RequestMapping(value = MyNumberUrl.BACK_TO_MYNUMBER_REGIST, method = RequestMethod.POST)
	public String back(Model model,
			@ModelAttribute("dependentsInfoListModel")DependentsInfoListModel lstDependentsInfo,
			BindingResult binding, HttpSession session,
			WebRequest request, @RequestParam("token") String requestToken) {

		// Check token
		if (!ValidateUtil.isValidToken("", request, requestToken, binding, model)) {
			return MyNumberJsp.DEPENDENTS_REGIST;
		}
				
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
			if (ValidateUtil.checkDependentsHasChange(listdDependents.get(i), staffSession) ||
					ValidateUtil.checkDependentsFormHasEdit(listdDependents.get(i))) {
				Collections.swap(listdDependents, i, swapIndex);
				swapIndex++;
			}
		}
		// store to session
		dependentsInfoListModel.setDependents(listdDependents);
		session.setAttribute("dependentsInfoListModel", dependentsInfoListModel);
	}

	/** Load default value for 10 form dependentsRegist
	 *
	 * @param staffInfoModel
	 * @return DependentsInfoListModel
	 */
	private DependentsInfoListModel initDataForm(StaffInfoModel staffInfoModel) {

		DependentsInfoListModel dependentsInfoListModel = new DependentsInfoListModel();
		List<Dependents> lstDependents = new ArrayList<Dependents>();
		Dependents dependents = new Dependents();
		dependents.setDependentsNameSei(staffInfoModel.getStaffNameSei());
		for (int i = 0; i < NUMBER_OF_DEPENDENT; i++) {
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
		for (int i = ConstValues.DEPENDENTS_MIN_YEAR; i <= curYear; i++) {
			lstYear.add("" + i);
		}
		model.addAttribute("listYear", lstYear);

		List<String> lstMonth = new ArrayList<String>();
		for (int i = ConstValues.DEPENDENTS_MIN_MONTH; i <= ConstValues.DEPENDENTS_MAX_MONTH; i++) {
			lstMonth.add("" + i);
		}
		model.addAttribute("listMonth", lstMonth);

		List<String> lstDay = new ArrayList<String>();
		for (int i = ConstValues.DEPENDENTS_MIN_DAY; i <= ConstValues.DEPENDENTS_MAX_DAY; i++) {
			lstDay.add("" + i);
		}
		model.addAttribute("listDay", lstDay);

		Map<String,String> relationship = new LinkedHashMap<String,String>();
		relationship.put(ConstValues.DEPENDENTS_RELATIONSHIP_01, ConstValues.DEPENDENTS_RELATIONSHIP_VALUE_01);
		relationship.put(ConstValues.DEPENDENTS_RELATIONSHIP_02, ConstValues.DEPENDENTS_RELATIONSHIP_VALUE_02);
		relationship.put(ConstValues.DEPENDENTS_RELATIONSHIP_03, ConstValues.DEPENDENTS_RELATIONSHIP_VALUE_03);
		relationship.put(ConstValues.DEPENDENTS_RELATIONSHIP_04, ConstValues.DEPENDENTS_RELATIONSHIP_VALUE_04);
		relationship.put(ConstValues.DEPENDENTS_RELATIONSHIP_05, ConstValues.DEPENDENTS_RELATIONSHIP_VALUE_05);
		relationship.put(ConstValues.DEPENDENTS_RELATIONSHIP_06, ConstValues.DEPENDENTS_RELATIONSHIP_VALUE_06);
		relationship.put(ConstValues.DEPENDENTS_RELATIONSHIP_07, ConstValues.DEPENDENTS_RELATIONSHIP_VALUE_07);

		model.addAttribute("listRelationship", relationship);

	}

}
