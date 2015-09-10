package com.athuman.mynumber.web.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.dto.Dependents;
import com.athuman.mynumber.web.dto.TACTMyNumberResponseDto;
import com.athuman.mynumber.web.dto.TACTRegistConfirmDto;
import com.athuman.mynumber.web.model.DependentsInfoListModel;
import com.athuman.mynumber.web.model.MyNumber;
import com.athuman.mynumber.web.model.ShainInfoModel;
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.service.MyNumberAPIService;
import com.athuman.mynumber.web.util.AESUtil;
import com.athuman.mynumber.web.util.ConstValues;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;

@Controller
public class RegistConfirmController {

	@Autowired(required=true)
	@Qualifier(value="myNumberAPIService")
	private MyNumberAPIService myNumberAPIService;

	public void setMyNumberAPIService(MyNumberAPIService myNumberAPIService) {
		this.myNumberAPIService = myNumberAPIService;
	}

	// show registConfirm page
	@RequestMapping(value = MyNumberUrl.REGISTCONFIRM, method = RequestMethod.GET)
	public String show(Model model, HttpSession session) {

		// get data form session.
		StaffInfoModel staffInfo = (StaffInfoModel)session.getAttribute("staffInfoModel");
		DependentsInfoListModel dependentInfo = (DependentsInfoListModel)session.getAttribute("dependentsInfoListModel");

		// init data for show jsp
		initData(model, staffInfo, dependentInfo);
		return MyNumberJsp.REGIST_CONFIRM;
	}

	@RequestMapping(value = MyNumberUrl.REGISTCONFIRM, method = RequestMethod.POST)
	public String next(@ModelAttribute("staffInfoModel") StaffInfoModel registConfirmForm,
			Model model, HttpSession session, BindingResult binding) {

		// Generator random UUID
		String uuid = UUID.randomUUID().toString();

		// get data form session
		StaffInfoModel staffInfo = (StaffInfoModel)session.getAttribute("staffInfoModel");
		DependentsInfoListModel dependentInfo = (DependentsInfoListModel)session.getAttribute("dependentsInfoListModel");
		ShainInfoModel shainInfoModel = (ShainInfoModel)session.getAttribute("shainInfoModel");

		// set data for TACT API
		TACTRegistConfirmDto tactRegistConfirmDto = new TACTRegistConfirmDto();
		tactRegistConfirmDto.setNo(uuid);
		tactRegistConfirmDto.setStaffNo(staffInfo.getStaffNo());
		tactRegistConfirmDto.setListDependent(dependentInfo.getDependents());

		// FIXME: Suppose we already had a service named [collectionInfo]
		TACTMyNumberResponseDto responseDto = myNumberAPIService.collectionInfo(tactRegistConfirmDto);
		// when status code != 200
		if (responseDto.getHttpStatus() != 200) {
			binding.rejectValue("staffSign", "S00001", new Object[] {"登録"}, null);
		}

		// regist to DB
		MyNumber myNumber = setData4MyNumber(staffInfo, uuid, shainInfoModel,
				dependentInfo, registConfirmForm.getStaffSign());

		// store data directly to DB
		String result = myNumberAPIService.registMyNumber(myNumber);
		if ("0".equals(result)) {
			binding.rejectValue("staffSign", "S00001", new Object[] {"登録"}, null);
			initData(model, staffInfo, dependentInfo);
			return MyNumberJsp.REGIST_CONFIRM;
		}
		return MyNumberJsp.REDIRECT_REGIST_COMPLETE;
	}

	@RequestMapping(value = MyNumberUrl.REGISTCONFIRM_BACK, method = RequestMethod.POST)
	public String back(Model model) {
		return MyNumberJsp.REDIRECT_STAFF_SIGNNING;
	}

	/** init data
	 *
	 * @param model
	 * @param staffInfo
	 * @param myNumberRegist
	 * @param dependentInfo
	 */
	private void initData(Model model, StaffInfoModel staffInfo,
			DependentsInfoListModel dependentInfo){
		model.addAttribute("staffName", getStaffName(staffInfo));

		if (staffInfo != null) {
			model.addAttribute("myNumberConfirm", getMyNumberConfirm(staffInfo));
			model.addAttribute("identification", getIdentification(staffInfo));
			model.addAttribute("myNumber", staffInfo.getMyNumber());
		}

		model.addAttribute("lstDependents", dependentInfo.getDependents());
		model.addAttribute("staffInfoModel", staffInfo);
	}

	/** get Identification from session
	 *
	 * @param myNumberRegist
	 * @return String
	 */
	private String getIdentification(StaffInfoModel myNumberRegist){
		List<String> lstIdentification = new ArrayList<String>();
		if (ConstValues.CHECKBOX_SELECT.equals(myNumberRegist.getDriversLicense())) {
			lstIdentification.add(ConstValues.DRIVERS_LICENSE);
		}

		if (ConstValues.CHECKBOX_SELECT.equals(myNumberRegist.getDriveHistoryLicense())) {
			lstIdentification.add(ConstValues.DRIVE_HISTORY_LICENSE);
		}

		if (ConstValues.CHECKBOX_SELECT.equals(myNumberRegist.getPassPort())) {
			lstIdentification.add(ConstValues.PASSPORT);
		}

		if (ConstValues.CHECKBOX_SELECT.equals(myNumberRegist.getBodyDisabilitiesNotebook())) {
			lstIdentification.add(ConstValues.BODY_DISABILITIES_NOTEBOOK);
		}

		if (ConstValues.CHECKBOX_SELECT.equals(myNumberRegist.getMentalDisabilitiesNotebook())) {
			lstIdentification.add(ConstValues.MENTAL_DISABILITIES_NOTEBOOK);
		}

		if (ConstValues.CHECKBOX_SELECT.equals(myNumberRegist.getRehabilitationNotebook())) {
			lstIdentification.add(ConstValues.REABILITATION_NOTEBOOK);
		}

		if (ConstValues.CHECKBOX_SELECT.equals(myNumberRegist.getStayCard())) {
			lstIdentification.add(ConstValues.STAY_CARD);
		}

		if (ConstValues.CHECKBOX_SELECT.equals(myNumberRegist.getClearPerson())) {
			lstIdentification.add(ConstValues.CLEAR_PERSON);
		}

		if (ConstValues.CHECKBOX_SELECT.equals(myNumberRegist.getHealthInsuranceLicense())) {
			lstIdentification.add(ConstValues.HEATH_INSURANCE_LICENSE);
		}

		if (ConstValues.CHECKBOX_SELECT.equals(myNumberRegist.getPensionNotebook())) {
			lstIdentification.add(ConstValues.PENSION_NOTBOOK);
		}

		if (ConstValues.CHECKBOX_SELECT.equals(myNumberRegist.getOther())) {
			lstIdentification.add(ConstValues.OTHER);
		}
		String result = lstIdentification.toString();
		return result.substring(1, (result.length() - 1));
	}

	/** get my number confirm from session
	 *
	 * @param myNumberRegist
	 * @return String
	 */
	private String getMyNumberConfirm(StaffInfoModel myNumberRegist){
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

	/** get staff name from session
	 *
	 * @param staffInfo
	 * @return String
	 */
	private String getStaffName(StaffInfoModel staffInfo){
		return staffInfo.getStaffNameSei() + "(" + staffInfo.getStaffNameSeiKana() + ")様";
	}

	/** set data for my number
	 *
	 * @param staffInfo
	 * @param uuid
	 * @param shainInfoModel
	 * @param myNumberRegist
	 * @param dependentInfo
	 * @return MyNumber
	 */
	private MyNumber setData4MyNumber(StaffInfoModel staffInfo, String uuid, ShainInfoModel shainInfoModel,
			DependentsInfoListModel dependentInfo, byte[] staffSign){
		MyNumber myNumber = new MyNumber();
		Date today = new Date();
		try {
			myNumber.setHimodukeNo(AESUtil.encrypt(uuid));

			List<Dependents> dependents = dependentInfo.getDependents();
			if (dependents != null) {
				myNumber.setFuyo1MyNumber(AESUtil.encrypt(dependents.get(0).getDependentsMyNumber()));
				myNumber.setFuyo2MyNumber(AESUtil.encrypt(dependents.get(1).getDependentsMyNumber()));
				myNumber.setFuyo3MyNumber(AESUtil.encrypt(dependents.get(2).getDependentsMyNumber()));
				myNumber.setFuyo4MyNumber(AESUtil.encrypt(dependents.get(3).getDependentsMyNumber()));
				myNumber.setFuyo5MyNumber(AESUtil.encrypt(dependents.get(4).getDependentsMyNumber()));
				myNumber.setFuyo6MyNumber(AESUtil.encrypt(dependents.get(5).getDependentsMyNumber()));
				myNumber.setFuyo7MyNumber(AESUtil.encrypt(dependents.get(6).getDependentsMyNumber()));
				myNumber.setFuyo8MyNumber(AESUtil.encrypt(dependents.get(7).getDependentsMyNumber()));
				myNumber.setFuyo9MyNumber(AESUtil.encrypt(dependents.get(8).getDependentsMyNumber()));
				myNumber.setFuyo10MyNumber(AESUtil.encrypt(dependents.get(9).getDependentsMyNumber()));
			}

			if (staffInfo != null) {
				myNumber.setStaffMyNumber(AESUtil.encrypt(staffInfo.getMyNumber()));
				myNumber.setMyNumberKakuninshorui(staffInfo.getMyNumberConfirm());
				myNumber.setUntenKeirekiShoumeisho(staffInfo.getDriveHistoryLicense());
				myNumber.setUntenMenkyyosho(staffInfo.getDriversLicense());
				myNumber.setPassport(staffInfo.getPassPort());
				myNumber.setShintaiShogaishaTecho(staffInfo.getBodyDisabilitiesNotebook());
				myNumber.setSeishinShogaishaTecho(staffInfo.getMentalDisabilitiesNotebook());
				myNumber.setRyoikuTecho(staffInfo.getRehabilitationNotebook());
				myNumber.setZairyuCard(staffInfo.getStayCard());
				myNumber.setHonninAkiraka(staffInfo.getClearPerson());
				myNumber.setKenkoHokenshasho(staffInfo.getHealthInsuranceLicense());
				myNumber.setNenkonTecho(staffInfo.getPensionNotebook());
				myNumber.setSonota(staffInfo.getOther());
			}

			myNumber.setHonninSyomei(staffSign);
			myNumber.setKakuninsha(shainInfoModel.getShainNo());
			myNumber.setTorokuUser(shainInfoModel.getShainNo());
			myNumber.setTorokuTimestamp(new Timestamp(today.getTime()));
			myNumber.setLastUpdateUser(shainInfoModel.getShainNo());
			myNumber.setLastUpdateTimeStamp(new Timestamp(today.getTime()));
			myNumber.setDeleteFlag("0");
			myNumber.setShodakuFlag(staffInfo.getConsent());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return myNumber;
	}
}
