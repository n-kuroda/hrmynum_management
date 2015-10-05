package com.athuman.mynumber.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.athuman.mynumber.web.dto.ColectionInfoDto;
import com.athuman.mynumber.web.dto.ColectionInfoRegistDto;
import com.athuman.mynumber.web.dto.Dependents;
import com.athuman.mynumber.web.model.DependentsInfoListModel;
import com.athuman.mynumber.web.model.MyNumber;
import com.athuman.mynumber.web.model.ShainInfoModel;
import com.athuman.mynumber.web.model.StaffInfoModel;
import com.athuman.mynumber.web.service.MyNumberAPIService;
import com.athuman.mynumber.web.util.AESUtil;
import com.athuman.mynumber.web.util.ConstValues;
import com.athuman.mynumber.web.util.MyNumberJsp;
import com.athuman.mynumber.web.util.MyNumberUrl;
import com.athuman.mynumber.web.util.StringUtil;
import com.athuman.mynumber.web.util.ValidateUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

@Controller
public class ColectionInfoRegistController {
	
	private static final int PREFIX_BASE64_TEXT_LENGTH = 22;
	private String autoUUID = "";

	@Autowired(required=true)
	@Qualifier(value="myNumberAPIService")
	private MyNumberAPIService myNumberAPIService;

	public void setMyNumberAPIService(MyNumberAPIService myNumberAPIService) {
		this.myNumberAPIService = myNumberAPIService;
	}

	// show colectionInfoRegist page
	@RequestMapping(value = MyNumberUrl.COLECTION_INFO_REGIST, method = RequestMethod.GET)
	public String show(Model model, HttpSession session) throws Exception {

		// check session has exist
		if (!ValidateUtil.isNotNullSession(session, model)) {
			return MyNumberJsp.REDIRECT_SHAIN_EXIST_CHECK;
		}

		initData(model, session);
		model.addAttribute("colectionInfoRegistDto",
				new ColectionInfoRegistDto());
		return MyNumberJsp.COLECTION_INFO_REGIST;
	}
	
	// submit colectionInfoRegist page
	@RequestMapping(value = MyNumberUrl.COLECTION_INFO_REGIST, method = RequestMethod.POST)
	public String regist(@RequestBody String miteikyoRiyu, HttpSession session, Model model) throws Exception {
		
		postRequest(session, model);

		ObjectMapper mapper = new ObjectMapper();
		ColectionInfoRegistDto colectionInfoRegistForm = mapper.readValue(miteikyoRiyu, ColectionInfoRegistDto.class);
		
		if (!ValidateUtil.isNotNullSession(session, model)) {
			return MyNumberJsp.REDIRECT_SHAIN_EXIST_CHECK;
		}

		// get data form session
		StaffInfoModel staffInfo = getStaffInfo((StaffInfoModel)session.getAttribute("staffInfoModel"));
		DependentsInfoListModel dependentInfo = (DependentsInfoListModel)session.getAttribute("dependentsInfoListModel");
		ShainInfoModel shainInfoModel = (ShainInfoModel)session.getAttribute("shainInfoModel");

		// regist to DB
		MyNumber myNumber = setData4MyNumber(staffInfo, autoUUID, shainInfoModel,
				dependentInfo, colectionInfoRegistForm);

		// store data directly to DB
		String result = myNumberAPIService.registMyNumber(myNumber);
		if (ConstValues.SAVE_DB_FAIL.equals(result)) {
			return ConstValues.COLLECT_INFO_REGIST_FAIL;
		} else {
			session.invalidate();
		}
		return MyNumberJsp.REDIRECT_REGIST_COMPLETE;
	}
	
	/** get StaffInfo when Identification has blank value
	 *
	 * @param staffInfo
	 * @return
	 */
	private StaffInfoModel getStaffInfo(StaffInfoModel staffInfo){

		staffInfo.setDriversLicense(checkIdentification(staffInfo.getDriversLicense()));
		staffInfo.setDriveHistoryLicense(checkIdentification(staffInfo.getDriveHistoryLicense()));
		staffInfo.setPassPort(checkIdentification(staffInfo.getPassPort()));
		staffInfo.setBodyDisabilitiesNotebook(checkIdentification(staffInfo.getBodyDisabilitiesNotebook()));
		staffInfo.setMentalDisabilitiesNotebook(checkIdentification(staffInfo.getMentalDisabilitiesNotebook()));
		staffInfo.setRehabilitationNotebook(checkIdentification(staffInfo.getRehabilitationNotebook()));
		staffInfo.setStayCard(checkIdentification(staffInfo.getStayCard()));
		staffInfo.setHealthInsuranceLicense(checkIdentification(staffInfo.getHealthInsuranceLicense()));
		staffInfo.setPensionNotebook(checkIdentification(staffInfo.getPensionNotebook()));
		staffInfo.setOther(checkIdentification(staffInfo.getOther()));

		return staffInfo;
	}

	/** check Identification is not blank
	 *
	 * @param string
	 * @return
	 */
	private String checkIdentification(String string){
		if (!StringUtil.isNotEmpty(string)) {
			return ConstValues.CHECKBOX_NOT_SELECT;
		}
		return string;
	}

	// Generator random UUID
	private String generateUUID() {
		return UUID.randomUUID().toString();
	}

	// convert to json object
	private String toJsonString(String str) {
		return str.replaceAll("\"", "'");
	}
	
	// get valid dependents list 
	private List<Dependents> getValidDependentsList(List<Dependents> dependents) {
		
		List<Dependents> retList = new ArrayList<Dependents>();
		if (dependents != null && dependents.size() > 0) {
			for (Dependents item: dependents) {
				if (StringUtil.isNotEmpty(item.getDependentsMyNumber())) {
					retList.add(item);
				}
			}
		}
		
		return retList;
	}
	
	/** Init data for jsp
	 *
	 * @param model
	 * @param session
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	private void initData(Model model, HttpSession session) throws Exception {

		StaffInfoModel staffInfoModel = (StaffInfoModel)session.getAttribute("staffInfoModel");
		if (staffInfoModel == null) {
			staffInfoModel = new StaffInfoModel();
		}
		
		DependentsInfoListModel dependentInfo = (DependentsInfoListModel)session.getAttribute("dependentsInfoListModel");
		
		autoUUID = generateUUID();

		// set data for API
		ColectionInfoDto colectionInfo = new ColectionInfoDto();
		colectionInfo.setHimodukeNo(autoUUID);
		colectionInfo.setStaffNo(staffInfoModel.getStaffNo());
		colectionInfo.setShodakuFlag(staffInfoModel.getConsent());
		
		List<Dependents> dependents = getValidDependentsList(dependentInfo.getDependents());
		if (dependents != null && dependents.size() > 0) {
			colectionInfo.setFuyoInfoList(dependents);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String colectionInfoJson = toJsonString(mapper.writeValueAsString(colectionInfo));
		  
		model.addAttribute("staffInfoModel", staffInfoModel);
		//store data to hidden field for passing data to API check
		model.addAttribute("colectionInfo", colectionInfoJson);
	}
	
	
	private void postRequest(HttpSession session,  Model model) throws Exception {
		// basicauth
		try {
			Authenticator.setDefault(new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("960228", "bgt54rfV"
							.toCharArray());
				}
			});
			
			// post request
			URL url = new URL("http://10.170.122.93/tact-hr/api/himoduke/");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");
			http.setRequestProperty("Accept-Language", "ja");
			OutputStream os = http.getOutputStream();
			PrintStream ps = new PrintStream(os);
			ps.print(createSendData(model, session));
			ps.close();

			// get response body
			InputStream is = http.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, StandardCharsets.UTF_8));
			StringBuilder sbBody = new StringBuilder();
			String s;
			while ((s = reader.readLine()) != null) {
				sbBody.append(s);
				sbBody.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String createSendData(Model model, HttpSession session) throws Exception{
		StaffInfoModel staffInfoModel = (StaffInfoModel)session.getAttribute("staffInfoModel");
		if (staffInfoModel == null) {
			staffInfoModel = new StaffInfoModel();
		}
		
		DependentsInfoListModel dependentInfo = (DependentsInfoListModel)session.getAttribute("dependentsInfoListModel");
		
		autoUUID = generateUUID();

		// set data for API
		ColectionInfoDto colectionInfo = new ColectionInfoDto();
		colectionInfo.setHimodukeNo(autoUUID);
		colectionInfo.setStaffNo(staffInfoModel.getStaffNo());
		colectionInfo.setShodakuFlag(staffInfoModel.getConsent());
		
		List<Dependents> dependents = getValidDependentsList(dependentInfo.getDependents());
		if (dependents != null && dependents.size() > 0) {
			colectionInfo.setFuyoInfoList(dependents);
		}
		
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper()
				.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		return mapper.writeValueAsString(colectionInfo);
		 
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
			DependentsInfoListModel dependentInfo, ColectionInfoRegistDto colectionInfoRegistForm) {

		MyNumber myNumber = new MyNumber();
		Date today = new Date();
		try {

			myNumber.setHimodukeNo(AESUtil.encrypt(uuid));
			if (ConstValues.CONSENT_VALUE_0.equals(staffInfo.getConsent())) {
				myNumber.setMiteikyoRiyu1(colectionInfoRegistForm.getMiteikyoRiyu1());
				myNumber.setMiteikyoRiyu2(colectionInfoRegistForm.getMiteikyoRiyu2());
				myNumber.setMiteikyoRiyu3(colectionInfoRegistForm.getMiteikyoRiyu3());
				myNumber.setMiteikyoRiyu4(colectionInfoRegistForm.getMiteikyoRiyu4());
			} else {
				myNumber.setMiteikyoRiyu1(ConstValues.CHECKBOX_NOT_SELECT);
				myNumber.setMiteikyoRiyu2(ConstValues.CHECKBOX_NOT_SELECT);
				myNumber.setMiteikyoRiyu3(ConstValues.CHECKBOX_NOT_SELECT);
				myNumber.setMiteikyoRiyu4(ConstValues.CHECKBOX_NOT_SELECT);
			}

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
				myNumber.setMyNumberKakuninTeijiShorui(staffInfo.getMyNumberConfirm());
				myNumber.setUntenKeirekiShoumeisho(staffInfo.getDriveHistoryLicense());
				myNumber.setUntenMenkyyosho(staffInfo.getDriversLicense());
				myNumber.setPassport(staffInfo.getPassPort());
				myNumber.setShintaiShogaishaTecho(staffInfo.getBodyDisabilitiesNotebook());
				myNumber.setSeishinShogaishaTecho(staffInfo.getMentalDisabilitiesNotebook());
				myNumber.setRyoikuTecho(staffInfo.getRehabilitationNotebook());
				myNumber.setZairyuCard(staffInfo.getStayCard());
				myNumber.setKenkoHokenshasho(staffInfo.getHealthInsuranceLicense());
				myNumber.setNenkonTecho(staffInfo.getPensionNotebook());
				myNumber.setSonota(staffInfo.getOther());
			}

			String staffSign = colectionInfoRegistForm.getStaffSign();
			staffSign = staffSign.substring(PREFIX_BASE64_TEXT_LENGTH);

			myNumber.setHonninSyomei(staffSign.getBytes());
			myNumber.setKakuninsha(shainInfoModel.getShainNo());
			myNumber.setTorokuUser(shainInfoModel.getShainNo());
			myNumber.setTorokuTimestamp(new Timestamp(today.getTime()));
			myNumber.setLastUpdateUser(shainInfoModel.getShainNo());
			myNumber.setLastUpdateTimeStamp(new Timestamp(today.getTime()));
			myNumber.setDeleteFlag(ConstValues.DELETE_FLAG_VALUES_0);
			myNumber.setShodakuFlag(staffInfo.getConsent());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return myNumber;
	}
}