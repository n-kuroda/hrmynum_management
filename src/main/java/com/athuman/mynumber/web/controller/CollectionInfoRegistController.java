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

import com.athuman.mynumber.web.dto.CollectionInfoDto;
import com.athuman.mynumber.web.dto.CollectionInfoRegistDto;
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
import com.athuman.mynumber.web.util.PropertyUtil;
import com.athuman.mynumber.web.util.StringUtil;
import com.athuman.mynumber.web.util.ValidateUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

@Controller
public class CollectionInfoRegistController {
	
	private static final int PREFIX_BASE64_TEXT_LENGTH = 22;
	private String autoUUID = "";

	@Autowired(required=true)
	@Qualifier(value="myNumberAPIService")
	private MyNumberAPIService myNumberAPIService;

	public void setMyNumberAPIService(MyNumberAPIService myNumberAPIService) {
		this.myNumberAPIService = myNumberAPIService;
	}

	// show collectionInfoRegist page
	@RequestMapping(value = MyNumberUrl.COLLECTION_INFO_REGIST, method = RequestMethod.GET)
	public String show(Model model, HttpSession session) throws Exception {

		// check session has exist
		if (!ValidateUtil.isNotNullSession(session, model)) {
			return MyNumberJsp.REDIRECT_SHAIN_EXIST_CHECK;
		}

		initData(model, session);
		model.addAttribute("collectionInfoRegistDto",
				new CollectionInfoRegistDto());
		return MyNumberJsp.COLLECTION_INFO_REGIST;
	}
	
	// submit collectionInfoRegist page
	@RequestMapping(value = MyNumberUrl.COLLECTION_INFO_REGIST, method = RequestMethod.POST)
	public String regist(@RequestBody String miteikyoRiyu, HttpSession session, Model model) throws Exception {
		
		postRequest(session, model);

		ObjectMapper mapper = new ObjectMapper();
		CollectionInfoRegistDto collectionInfoRegistForm = mapper.readValue(miteikyoRiyu, CollectionInfoRegistDto.class);
		
		if (!ValidateUtil.isNotNullSession(session, model)) {
			return MyNumberJsp.REDIRECT_SHAIN_EXIST_CHECK;
		}

		// get data form session
		StaffInfoModel staffInfo = getStaffInfo((StaffInfoModel)session.getAttribute("staffInfoModel"));
		DependentsInfoListModel dependentInfo = (DependentsInfoListModel)session.getAttribute("dependentsInfoListModel");
		ShainInfoModel shainInfoModel = (ShainInfoModel)session.getAttribute("shainInfoModel");

		// regist to DB
		MyNumber myNumber = setData4MyNumber(staffInfo, autoUUID, shainInfoModel,
				dependentInfo, collectionInfoRegistForm);

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
				if (StringUtil.isNotEmpty(item.getFuyoMyNumber())) {
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
		CollectionInfoDto collectionInfo = new CollectionInfoDto();
		collectionInfo.setHimodukeNo(autoUUID);
		collectionInfo.setStaffNo(staffInfoModel.getStaffNo());
		collectionInfo.setShodakuFlag(staffInfoModel.getConsent());
		
		List<Dependents> dependents = getValidDependentsList(dependentInfo.getDependents());
		if (dependents != null && dependents.size() > 0) {
			collectionInfo.setFuyoInfoList(dependents);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String collectionInfoJson = toJsonString(mapper.writeValueAsString(collectionInfo));
		  
		model.addAttribute("staffInfoModel", staffInfoModel);
		//store data to hidden field for passing data to API check
		model.addAttribute("collectionInfo", collectionInfoJson);
	}
	
	
	private void postRequest(HttpSession session,  Model model) throws Exception {
		// basicauth
		try {
			final String userName = PropertyUtil.getProperties("application.properties","basicauth.username");
			final String password = PropertyUtil.getProperties("application.properties","basicauth.password");
			
			Authenticator.setDefault(new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password
							.toCharArray());
				}
			});
			
			// post request
			URL url = new URL(PropertyUtil.getProperties("application.properties","tact.api.himoduke.url"));
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json;charset=sjis");
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
		CollectionInfoDto collectionInfo = new CollectionInfoDto();
		collectionInfo.setHimodukeNo(autoUUID);
		collectionInfo.setStaffNo(staffInfoModel.getStaffNo());
		collectionInfo.setShodakuFlag(staffInfoModel.getConsent());
		
		List<Dependents> dependents = getValidDependentsList(dependentInfo.getDependents());
		if (dependents != null && dependents.size() > 0) {
			collectionInfo.setFuyoInfoList(dependents);
		}
		
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper()
				.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		return mapper.writeValueAsString(collectionInfo);
		 
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
			DependentsInfoListModel dependentInfo, CollectionInfoRegistDto collectionInfoRegistForm) {

		MyNumber myNumber = new MyNumber();
		Date today = new Date();
		try {

			myNumber.setHimodukeNo(AESUtil.encrypt(uuid));
			if (ConstValues.CONSENT_VALUE_0.equals(staffInfo.getConsent())) {
				myNumber.setMiteikyoRiyu1(collectionInfoRegistForm.getMiteikyoRiyu1());
				myNumber.setMiteikyoRiyu2(collectionInfoRegistForm.getMiteikyoRiyu2());
				myNumber.setMiteikyoRiyu3(collectionInfoRegistForm.getMiteikyoRiyu3());
				myNumber.setMiteikyoRiyu4(collectionInfoRegistForm.getMiteikyoRiyu4());
				myNumber.setMiteikyoRiyu5(collectionInfoRegistForm.getMiteikyoRiyu5());
				myNumber.setMiteikyoRiyu6(collectionInfoRegistForm.getMiteikyoRiyu6());
			} else {
				myNumber.setMiteikyoRiyu1(ConstValues.CHECKBOX_NOT_SELECT);
				myNumber.setMiteikyoRiyu2(ConstValues.CHECKBOX_NOT_SELECT);
				myNumber.setMiteikyoRiyu3(ConstValues.CHECKBOX_NOT_SELECT);
				myNumber.setMiteikyoRiyu4(ConstValues.CHECKBOX_NOT_SELECT);
				myNumber.setMiteikyoRiyu5(ConstValues.CHECKBOX_NOT_SELECT);
				myNumber.setMiteikyoRiyu6(null);
			}

			List<Dependents> dependents = dependentInfo.getDependents();
			if (dependents != null) {
				myNumber.setFuyo1MyNumber(AESUtil.encrypt(dependents.get(0).getFuyoMyNumber()));
				myNumber.setFuyo2MyNumber(AESUtil.encrypt(dependents.get(1).getFuyoMyNumber()));
				myNumber.setFuyo3MyNumber(AESUtil.encrypt(dependents.get(2).getFuyoMyNumber()));
				myNumber.setFuyo4MyNumber(AESUtil.encrypt(dependents.get(3).getFuyoMyNumber()));
				myNumber.setFuyo5MyNumber(AESUtil.encrypt(dependents.get(4).getFuyoMyNumber()));
				myNumber.setFuyo6MyNumber(AESUtil.encrypt(dependents.get(5).getFuyoMyNumber()));
				myNumber.setFuyo7MyNumber(AESUtil.encrypt(dependents.get(6).getFuyoMyNumber()));
				myNumber.setFuyo8MyNumber(AESUtil.encrypt(dependents.get(7).getFuyoMyNumber()));
				myNumber.setFuyo9MyNumber(AESUtil.encrypt(dependents.get(8).getFuyoMyNumber()));
				myNumber.setFuyo10MyNumber(AESUtil.encrypt(dependents.get(9).getFuyoMyNumber()));
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

			String staffSign = collectionInfoRegistForm.getStaffSign();
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