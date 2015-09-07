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
import com.athuman.mynumber.web.dto.RegistConfirmDto;
import com.athuman.mynumber.web.dto.TACTMyNumberResponseDto;
import com.athuman.mynumber.web.dto.TACTRegistConfirmDto;
import com.athuman.mynumber.web.model.DependentsInfoListModel;
import com.athuman.mynumber.web.model.MyNumber;
import com.athuman.mynumber.web.model.ShainInfoModel;
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.service.MyNumberService;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;

@Controller
public class RegistConfirmController {

	@Autowired(required=true)
	@Qualifier(value="myNumberService")
	private MyNumberService myNumberService;

	public void setMyNumberService(MyNumberService myNumberService) {
		this.myNumberService = myNumberService;
	}

	// show registConfirm page
	@RequestMapping(value = MyNumberUrl.REGISTCONFIRM, method = RequestMethod.GET)
	public String show(Model model, HttpSession session) {

		// get data form session.
		StaffInfoModel staffInfo = (StaffInfoModel)session.getAttribute("staffInfoModel");
		StaffInfoModel myNumberRegist = (StaffInfoModel)session.getAttribute("myNumerRegistModel");
		DependentsInfoListModel dependentInfo = (DependentsInfoListModel)session.getAttribute("lstDependentsSesion");

		// init data for show jsp
		initData(model, staffInfo, myNumberRegist, dependentInfo);
		return MyNumberJsp.REGIST_CONFIRM;
	}

	@RequestMapping(value = MyNumberUrl.REGISTCONFIRM, method = RequestMethod.POST)
	public String next(@ModelAttribute("registConfirmDto")RegistConfirmDto registConfirmDto,
			Model model, HttpSession session, BindingResult binding) {

		// Generator random UUID
		String uuid = UUID.randomUUID().toString();

		// get data form session
		StaffInfoModel staffInfo = (StaffInfoModel)session.getAttribute("staffInfoModel");
		StaffInfoModel myNumberRegist = (StaffInfoModel)session.getAttribute("myNumerRegistModel");
		DependentsInfoListModel dependentInfo = (DependentsInfoListModel)session.getAttribute("lstDependentsSesion");
		ShainInfoModel shainInfoModel = (ShainInfoModel)session.getAttribute("shainInfoModel");

		// set data for TACT API
		TACTRegistConfirmDto tactRegistConfirmDto = new TACTRegistConfirmDto();
		tactRegistConfirmDto.setNo(uuid);
		tactRegistConfirmDto.setStaffNo(staffInfo.getStaffNo());
		tactRegistConfirmDto.setListDependent(dependentInfo.getDependents());

		// Call TACT API
		TACTMyNumberResponseDto responseDto = myNumberService.registrationInformationCollected(tactRegistConfirmDto);
		// when status code != 200
		if (responseDto.getHttpStatus() != 200) {
			binding.rejectValue("staffName", "S00001", new Object[] {"登録"}, null);
		}

		// regist to DB
		MyNumber myNumber = setData4MyNumber(staffInfo, uuid, shainInfoModel, myNumberRegist, dependentInfo, registConfirmDto);

		String result = myNumberService.registMyNumber(myNumber);
		if ("0".equals(result)) {
			binding.rejectValue("staffName", "S00001", new Object[] {"登録"}, null);
			initData(model, staffInfo, myNumberRegist, dependentInfo);
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
	private void initData(Model model, StaffInfoModel staffInfo, StaffInfoModel myNumberRegist,
			DependentsInfoListModel dependentInfo){
		RegistConfirmDto registConfirmDto = new RegistConfirmDto();
		registConfirmDto.setStaffName(getStaffName(staffInfo));
		
		if (myNumberRegist != null) {
			registConfirmDto.setMyNumberConfirm(getMyNumberConfirm(myNumberRegist));
			registConfirmDto.setIdentification(getIdentification(myNumberRegist));
			registConfirmDto.setMyNumber(myNumberRegist.getMyNumber());
		}

		registConfirmDto.setLstDependents(dependentInfo.getDependents());
		model.addAttribute("registConfirmDto", registConfirmDto );
	}

	/** get Identification from session
	 *
	 * @param myNumberRegist
	 * @return String
	 */
	private String getIdentification(StaffInfoModel myNumberRegist){
		List<String> lstIdentification = new ArrayList<String>();
		if ("1".equals(myNumberRegist.getDriversLicense())) {
			lstIdentification.add("運転免許証");
		}

		if ("1".equals(myNumberRegist.getDriveHistoryLicense())) {
			lstIdentification.add("運転経歴証明書");
		}

		if ("1".equals(myNumberRegist.getDriversLicense())) {
			lstIdentification.add("運転免許証");
		}

		if ("1".equals(myNumberRegist.getPassPort())) {
			lstIdentification.add("パスポート");
		}

		if ("1".equals(myNumberRegist.getBodyDisabilitiesNotebook())) {
			lstIdentification.add("身体障碍者手帳");
		}

		if ("1".equals(myNumberRegist.getMentalDisabilitiesNotebook())) {
			lstIdentification.add("精神障碍者保健福祉手帳");
		}

		if ("1".equals(myNumberRegist.getRehabilitationNotebook())) {
			lstIdentification.add("療育手帳");
		}

		if ("1".equals(myNumberRegist.getStayCard())) {
			lstIdentification.add("在留カード");
		}

		if ("1".equals(myNumberRegist.getClearPerson())) {
			lstIdentification.add("本人であることが明らか");
		}

		if ("1".equals(myNumberRegist.getHealthInsuranceLicense())) {
			lstIdentification.add("健康保険被保険者証");
		}

		if ("1".equals(myNumberRegist.getPensionNotebook())) {
			lstIdentification.add("年金手帳");
		}

		if ("1".equals(myNumberRegist.getOther())) {
			lstIdentification.add("その他");
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
		if ("01".equals(myNumberRegist.getMyNumberConfirm())) {
			return "個人番号カード";
		} else if ("02".equals(myNumberRegist.getMyNumberConfirm())) {
			return "通知カード";
		} else if ("03".equals(myNumberRegist.getMyNumberConfirm())) {
			return "番号が記載された住民票のコピー";
		} else {
			return "番号が記載された住民票記載事項証明書";
		}
	}

	/** get staff name from session
	 *
	 * @param staffInfo
	 * @return String
	 */
	private String getStaffName(StaffInfoModel staffInfo){
		return staffInfo.getStaffName() + "(" + staffInfo.getStaffNameKana() + ")様";
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
			StaffInfoModel myNumberRegist, DependentsInfoListModel dependentInfo, RegistConfirmDto registConfirmDto){
		MyNumber myNumber = new MyNumber();
		Date today = new Date();
		myNumber.setHimodukeNo(uuid);
		
		List<Dependents> dependents = dependentInfo.getDependents();
		if (dependents != null) {
			myNumber.setFuyo1MyNumber(dependents.get(0).getDependentsMyNumber());
			myNumber.setFuyo2MyNumber(dependents.get(1).getDependentsMyNumber());
			myNumber.setFuyo3MyNumber(dependents.get(2).getDependentsMyNumber());
			myNumber.setFuyo4MyNumber(dependents.get(3).getDependentsMyNumber());
			myNumber.setFuyo5MyNumber(dependents.get(4).getDependentsMyNumber());
			myNumber.setFuyo6MyNumber(dependents.get(5).getDependentsMyNumber());
			myNumber.setFuyo7MyNumber(dependents.get(6).getDependentsMyNumber());
			myNumber.setFuyo8MyNumber(dependents.get(7).getDependentsMyNumber());
			myNumber.setFuyo9MyNumber(dependents.get(8).getDependentsMyNumber());
			myNumber.setFuyo10MyNumber(dependents.get(9).getDependentsMyNumber());
		}

		if (myNumberRegist != null) {
			myNumber.setStaffMyNumber(myNumberRegist.getMyNumber());
			myNumber.setMyNumberKakuninshorui(myNumberRegist.getMyNumberConfirm());
			myNumber.setUntenKeirekiShoumeisho(myNumberRegist.getDriveHistoryLicense());
			myNumber.setUntenMenkyyosho(myNumberRegist.getDriversLicense());
			myNumber.setPassport(myNumberRegist.getPassPort());
			myNumber.setShintaiShogaishaTecho(myNumberRegist.getBodyDisabilitiesNotebook());
			myNumber.setSeishinShogaishaTecho(myNumberRegist.getMentalDisabilitiesNotebook());
			myNumber.setRyoikuTecho(myNumberRegist.getRehabilitationNotebook());
			myNumber.setZairyuCard(myNumberRegist.getStayCard());
			myNumber.setHonninAkiraka(myNumberRegist.getClearPerson());
			myNumber.setKenkoHokenshasho(myNumberRegist.getHealthInsuranceLicense());
			myNumber.setNenkonTecho(myNumberRegist.getPensionNotebook());
			myNumber.setSonota(myNumberRegist.getOther());
		}

		myNumber.setHonninSyomei(registConfirmDto.getStaffSignning());
		myNumber.setKakuninsha("123456");// unknow field
		myNumber.setTorokuUser(shainInfoModel.getShainNo());
		myNumber.setTorokuTimestamp(new Timestamp(today.getTime()));
		myNumber.setLastUpdateUser("User");// unknow field
		myNumber.setLastUpdateTimeStamp(new Timestamp(today.getTime()));
		myNumber.setDeleteFlag("0");
		return myNumber;
	}
}
