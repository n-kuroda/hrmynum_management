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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.athuman.mynumber.web.model.DependentsInfoListModel;
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.util.ConstValues;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.ValidateUtil;

@Controller
public class StaffRegistConfirmController {

	// show registConfirm page
	@RequestMapping(value = MyNumberUrl.STAFF_REGIST_CONFIRM, method = RequestMethod.GET)
	public String show(Model model, HttpSession session, @RequestParam("token") String requestToken) {

		model.addAttribute("token", requestToken);
		return resetData(model, session);
	}

	@RequestMapping(value = MyNumberUrl.STAFF_REGIST_CONFIRM, method = RequestMethod.POST)
	public String confirm(Model model,
			HttpSession session,
			@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModel,
			BindingResult binding, 
			WebRequest request, 
			@RequestParam("token") String requestToken) {
		
		// Check token
		if (!ValidateUtil.isValidToken("", request, requestToken, binding, model)) {
			return resetData(model, session);
		}
				
		return MyNumberJsp.REDIRECT_STAFF_SIGNING;
	}

	@RequestMapping(value = MyNumberUrl.STAFF_REGIST_CONFIRM_BACK, method = RequestMethod.POST)
	public String back(Model model, 
			HttpSession session,
			@ModelAttribute("staffInfoModel") StaffInfoModel staffInfoModel,
			BindingResult binding, 
			WebRequest request, 
			@RequestParam("token") String requestToken) {
		
		// Check token
		if (!ValidateUtil.isValidToken("", request, requestToken, binding, model)) {
			return resetData(model, session);
		}

		StaffInfoModel staffInfoModelSession = (StaffInfoModel) session.getAttribute("staffInfoModel");
		if (ConstValues.CONSENT_VALUE_0.equals(staffInfoModelSession.getConsent())) {
			return MyNumberJsp.REDIRECT_PURPOSE_CONSENT;
		}
		return MyNumberJsp.REDIRECT_DEPENDENTS_REGIST;
	}

	/**
	 * init data
	 *
	 * @param model
	 * @param staffInfo
	 * @param myNumberRegist
	 * @param dependentInfo
	 */
	private void initData(Model model, StaffInfoModel staffInfo,
			DependentsInfoListModel dependentInfo) {

		model.addAttribute("staffName", getStaffName(staffInfo));

		if (staffInfo != null) {
			model.addAttribute("myNumberConfirm", getMyNumberConfirm(staffInfo));
			model.addAttribute("identification", getIdentification(staffInfo));
			model.addAttribute("myNumber", staffInfo.getMyNumber());
		}

		model.addAttribute("lstDependents", dependentInfo.getDependents());
		model.addAttribute("staffInfo", staffInfo);
	}

	/**
	 * get Identification from session
	 *
	 * @param myNumberRegist
	 * @return String
	 */
	private List<String> getIdentification(StaffInfoModel myNumberRegist) {
		List<String> lstIdentification = new ArrayList<String>();

		// add 運転免許証 if check box Drivers License has checked
		lstIdentification = checkIdentification(
				myNumberRegist.getDriversLicense(), lstIdentification,
				ConstValues.DRIVERS_LICENSE);

		// add 運転経歴証明書 if check box DriveHistory License has checked
		lstIdentification = checkIdentification(
				myNumberRegist.getDriveHistoryLicense(), lstIdentification,
				ConstValues.DRIVE_HISTORY_LICENSE);

		// add パスポート if check box Passport has checked
		lstIdentification = checkIdentification(myNumberRegist.getPassPort(),
				lstIdentification, ConstValues.PASSPORT);

		// add 身体障碍者手帳 if check box Body Disabilities Notebook has checked
		lstIdentification = checkIdentification(
				myNumberRegist.getBodyDisabilitiesNotebook(),
				lstIdentification, ConstValues.BODY_DISABILITIES_NOTEBOOK);

		// add 精神障碍者保健福祉手帳 if check box Mental Disabilities Notebook has checked
		lstIdentification = checkIdentification(
				myNumberRegist.getMentalDisabilitiesNotebook(),
				lstIdentification, ConstValues.MENTAL_DISABILITIES_NOTEBOOK);

		// add 療育手帳 if check box Rehabilitation Notebook has checked
		lstIdentification = checkIdentification(
				myNumberRegist.getRehabilitationNotebook(), lstIdentification,
				ConstValues.REABILITATION_NOTEBOOK);

		// add 在留カード if check box Stay Card has checked
		lstIdentification = checkIdentification(myNumberRegist.getStayCard(),
				lstIdentification, ConstValues.STAY_CARD);

		// add 健康保険被保険者証 if check box Health Insurance License has checked
		lstIdentification = checkIdentification(
				myNumberRegist.getHealthInsuranceLicense(), lstIdentification,
				ConstValues.HEATH_INSURANCE_LICENSE);

		// add 年金手帳 if check box Pension Notebook has checked
		lstIdentification = checkIdentification(
				myNumberRegist.getPensionNotebook(), lstIdentification,
				ConstValues.PENSION_NOTBOOK);

		// add その他 if check box Other has checked
		lstIdentification = checkIdentification(myNumberRegist.getOther(),
				lstIdentification, ConstValues.OTHER);

		return lstIdentification;
	}

	/**
	 * Add data if check box has checked
	 *
	 * @param item
	 * @param lts
	 * @param itemName
	 * @return List<String>
	 */
	private List<String> checkIdentification(String item, List<String> itemList,
			String itemName) {
		if (ConstValues.CHECKBOX_SELECT.equals(item)) {
			itemList.add(itemName);
			return itemList;
		} else {
			return itemList;
		}

	}

	/**
	 * get my number confirm from session
	 *
	 * @param myNumberRegist
	 * @return String
	 */
	private String getMyNumberConfirm(StaffInfoModel myNumberRegist) {
		if (ConstValues.MY_NUMBER_CONFIRM_01.equals(myNumberRegist.getMyNumberConfirm())) {
			return ConstValues.MY_NUMBER_CONFIRM_VALUE_01;
		} else if (ConstValues.MY_NUMBER_CONFIRM_02.equals(myNumberRegist.getMyNumberConfirm())) {
			return ConstValues.MY_NUMBER_CONFIRM_VALUE_02;
		} else if (ConstValues.MY_NUMBER_CONFIRM_03.equals(myNumberRegist.getMyNumberConfirm())) {
			return ConstValues.MY_NUMBER_CONFIRM_VALUE_03;
		} else {
			return ConstValues.MY_NUMBER_CONFIRM_VALUE_04;
		}
	}

	/**
	 * get staff name from session
	 *
	 * @param staffInfo
	 * @return String
	 */
	private String getStaffName(StaffInfoModel staffInfo) {
		return staffInfo.getStaffNameSei() + " " + staffInfo.getStaffNameMei() +
				"("+ staffInfo.getStaffNameSeiKana() + " " + staffInfo.getStaffNameMeiKana() + ")";
	}
	
	/**
	 * reset data when load page
	 *
	 * @param model
	 * @param session
	 * @return String
	 */
	private String resetData(Model model, HttpSession session){
		// get data form session.
		StaffInfoModel staffInfo = (StaffInfoModel) session.getAttribute("staffInfoModel");
		DependentsInfoListModel dependentInfo = (DependentsInfoListModel) session.getAttribute("dependentsInfoListModel");

		// init data for show jsp
		initData(model, staffInfo, dependentInfo);
		return MyNumberJsp.STAFF_REGIST_CONFIRM;
	}
	
}
