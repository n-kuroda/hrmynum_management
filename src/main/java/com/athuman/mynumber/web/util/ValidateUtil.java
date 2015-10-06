package com.athuman.mynumber.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;

import com.athuman.mynumber.web.dto.Dependents;
import com.athuman.mynumber.web.model.ShainInfoModel;
import com.athuman.mynumber.web.model.StaffInfoModel;

public class ValidateUtil {

	/* check input valid */
	public static BindingResult checkInputValid(String item, String itemName,
			String itemValue, BindingResult bindingResult, int digit) {

		/* check not null */
		if (itemValue.trim().equals("")) {
			bindingResult.rejectValue(item, "V00001",
					new Object[] { itemName }, null);
		} else {
			/* check is number 1byte*/
			if (!is1ByteCharacters(itemValue)) {
				bindingResult.rejectValue(item, "V00003",
						new Object[] { itemName }, null);
			}
			/* check length */
			if (itemValue.length() != digit || !isNumeric(itemValue) ) {
				bindingResult.rejectValue(item, "V00002", new Object[] {
						itemName, digit }, null);
			}
		}
		return bindingResult;
	}
	/** validate for form Dependents
	 *
	 * @param bindingResult
	 * @param dependents
	 * @param staffInfoModel
	 * @param index
	 * @return BindingResult
	 */
	public static BindingResult validFormDependents(BindingResult bindingResult,
			Dependents dependents, StaffInfoModel staffInfoModel, int index) {

		// check form Dependents has change or form has edit
		if (checkDependentsHasChange(dependents, staffInfoModel) ||checkDependentsFormHasEdit(dependents)) {

			checkNameSei(dependents, bindingResult, index);

			checkNameMei(dependents, bindingResult, index);

			checkDate(dependents, bindingResult, index);

			checkRelationship(dependents, bindingResult, index);

			checkMyNumber(dependents, bindingResult, index);
			
		}

		return bindingResult;
	}

	/** check year in range
	 *
	 * @param year
	 * @return boolean
	 */
	public static boolean checkYearInvalidRange(String year) {
		// get current year
		int curYear = Calendar.getInstance().get(Calendar.YEAR);
		int yearToCheck = 0;
		try {
			yearToCheck = Integer.parseInt(year);
		} catch (NumberFormatException e) {
			return true;
		}
		if (yearToCheck >= ConstValues.DEPENDENTS_MIN_YEAR && yearToCheck <= curYear) {
			return false;
		}
		return true;
	}

	/** check month in range
	 *
	 * @param month
	 * @return boolean
	 */
	public static boolean checkMonthInvalidRange(String month) {

		int monthToCheck = 0;
		try {
			monthToCheck = Integer.parseInt(month);
		} catch (NumberFormatException e) {
			return true;
		}
		if (monthToCheck > ConstValues.DEPENDENTS_MIN_MONTH_CHECK &&
				monthToCheck <= ConstValues.DEPENDENTS_MAX_MONTH) {
			return false;
		}
		return true;
	}

	/** check day in range
	 *
	 * @param day
	 * @return boolean
	 */
	public static boolean checkDayInvalidRange(String day) {

		int dayToCheck = 0;
		try {
			dayToCheck = Integer.parseInt(day);
		} catch (NumberFormatException e) {
			return true;
		}
		if (dayToCheck > ConstValues.DEPENDENTS_MIN_DAY_CHECK &&
				dayToCheck <= ConstValues.DEPENDENTS_MAX_DAY) {
			return false;
		}
		return true;
	}

	/** check relationship is valid
	 *
	 * @param relation
	 * @return boolean
	 */
	public static boolean checkRelationshipInvalid(String relation) {

		String[] stringRelation =
			{
				ConstValues.DEPENDENTS_RELATIONSHIP_01,
				ConstValues.DEPENDENTS_RELATIONSHIP_02,
				ConstValues.DEPENDENTS_RELATIONSHIP_03,
				ConstValues.DEPENDENTS_RELATIONSHIP_04,
				ConstValues.DEPENDENTS_RELATIONSHIP_05,
				ConstValues.DEPENDENTS_RELATIONSHIP_06,
				ConstValues.DEPENDENTS_RELATIONSHIP_07
			};
		for (int i = 0; i < stringRelation.length; i++) {
			if (stringRelation[i].equals(relation)) {
				return false;
			}
		}
		return true;
	}

	/**check day exist in future
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return boolean
	 */
	public static boolean isThisDateValid(String year, String month, String day) {

		String dateFormat = "yyyy/MM/dd";
		String dateToValidate = year + "/" + month + "/" + day;

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);

		try {

			// if not valid, it will throw ParseException
			sdf.parse(dateToValidate);

		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	/** check staff name has change
	 *
	 * @param dependents
	 * @param staffInfoModel
	 * @return boolean
	 */
	public static boolean checkDependentsHasChange(Dependents dependents, StaffInfoModel staffInfoModel){
		if (!dependents.getFuyoNameSei().equals(staffInfoModel.getStaffNameSei())
				&& !ConstValues.BLANK.equals(dependents.getFuyoNameSei())) {
			return true;
		}
		return false;
	}

	/** check form has edit
	 *
	 * @param dependents
	 * @return boolean
	 */
	public static boolean checkDependentsFormHasEdit(Dependents dependents){

		if (!ConstValues.BLANK.equals(dependents.getFuyoNameMei()) ||
			!ConstValues.BLANK.equals(dependents.getFuyoSeinengapiYear()) ||
			!ConstValues.BLANK.equals(dependents.getFuyoSeinengapiMonth()) ||
			!ConstValues.BLANK.equals(dependents.getFuyoSeinengapiDay()) ||
			!ConstValues.BLANK.equals(dependents.getFuyoZokugara()) ||
			!ConstValues.BLANK.equals(dependents.getFuyoMyNumber()) ||
			ConstValues.CHECKBOX_SELECT.equals(dependents.getDaisangoHihokensha())) {
			return true;
		}

		if (ConstValues.DEPENDENTS_RELATIONSHIP_07.equals(dependents.getFuyoZokugara()) &&
				ConstValues.BLANK.equals(dependents.getFuyoZokugaraSonota())) {
			return true;
		}

		if (!ConstValues.DEPENDENTS_RELATIONSHIP_07.equals(dependents.getFuyoZokugara()) &&
				!ConstValues.BLANK.equals(dependents.getFuyoZokugaraSonota())) {
			return true;
		}

		return false;
	}

	/** check string is 2 byte character
	 *
	 * @param value
	 * @return boolean
	 */
	public static boolean is2ByteCharacters(String value) {

		int MAX_LENGTH_CHAR = 255;
		boolean isValid = false;
		char[] arrayValue = value.toCharArray();
		for (int i = 0; i < arrayValue.length; i++) {
			if (arrayValue[i] > MAX_LENGTH_CHAR) {
				isValid = true;
			} else {
				return false;
			}
		}

		return isValid;
	}

	/** check string is 1 byte character
	 *
	 * @param value
	 * @return boolean
	 */
	public static boolean is1ByteCharacters(String value) {
		int MAX_LENGTH_CHAR = 255;
		boolean isValid = false;
		char[] arrayValue = value.toCharArray();
		for (int i = 0; i < arrayValue.length; i++) {
			if (arrayValue[i] < MAX_LENGTH_CHAR) {
				isValid = true;
			} else {
				return false;
			}
		}
		return isValid;
	}

	/** check string is number
	 *
	 * @param value
	 * @return boolean
	 */
	public static boolean isNumeric(String str) {
	    for (char c : str.toCharArray()) {
	        if (!Character.isDigit(c)) {
	        	return false;
	        }
	    }
	    return true;
	}

	public static BindingResult checkNameSei(Dependents dependents, BindingResult bindingResult, int index) {
		if (!ConstValues.BLANK.equals(dependents.getFuyoNameSei())) {

			// check fuyoNameSei > 25 characters
			if (dependents.getFuyoNameSei().length() > 25) {
				bindingResult.rejectValue("dependents[" + index + "].fuyoNameSei",
						"V00010", new Object[] {"扶養者" + (index + 1), "お名前（姓）", "25"}, null );
			}
			// check format nameSei
			if (!is2ByteCharacters(dependents.getFuyoNameSei())) {
				bindingResult.rejectValue("dependents[" + index + "].fuyoNameSei",
						"V00011", new Object[] {"扶養者" + (index + 1), "お名前（姓）"}, null );
			}
		} else {
			bindingResult.rejectValue("dependents[" + index + "].fuyoNameSei",
					"V00001", new Object[] {"[扶養者" + (index + 1) + "]お名前（姓）"}, null );
		}
		return bindingResult;
	}

	public static BindingResult checkNameMei(Dependents dependents, BindingResult bindingResult, int index) {
		if (!ConstValues.BLANK.equals(dependents.getFuyoNameMei())) {
			// check fuyoNameMei > 25 characters
			if (dependents.getFuyoNameMei().length() > 25) {
				bindingResult.rejectValue("dependents[" + index + "].fuyoNameMei",
						"V00010", new Object[] {"扶養者" + (index + 1), "お名前（名）", "25"}, null );
			}

			// check format nameMei
			if (!is2ByteCharacters(dependents.getFuyoNameMei())) {
				bindingResult.rejectValue("dependents[" + index + "].fuyoNameMei",
						"V00011", new Object[] {"扶養者" + (index + 1), "お名前（名）"}, null );
			}
		} else {
			bindingResult.rejectValue("dependents[" + index + "].fuyoNameMei",
					"V00001", new Object[] {"[扶養者" + (index + 1) + "]お名前（名）"}, null );
		}
		return bindingResult;
	}

	public static BindingResult checkDate(Dependents dependents,
			BindingResult bindingResult, int index) {
		// check year invalid
		if (!ConstValues.BLANK.equals(dependents.getFuyoSeinengapiYear())
				&& checkYearInvalidRange(dependents.getFuyoSeinengapiYear())) {
			bindingResult.rejectValue("dependents[" + index + "].fuyoSeinengapiYear",
					"V00013", new Object[] {"扶養者" + (index + 1), "生年月日（年）"}, null );
		} else if (ConstValues.BLANK.equals(dependents.getFuyoSeinengapiYear())) {
			bindingResult.rejectValue("dependents[" + index + "].fuyoSeinengapiYear",
					"V00001", new Object[] {"[扶養者" + (index + 1) + "]生年月日（年）"}, null );
		}

		// check month invalid
		if (!ConstValues.BLANK.equals(dependents.getFuyoSeinengapiMonth())
				&& checkMonthInvalidRange(dependents.getFuyoSeinengapiMonth())) {
			bindingResult.rejectValue("dependents[" + index + "].fuyoSeinengapiMonth",
					"V00013", new Object[] {"扶養者" + (index + 1), "生年月日（月）"}, null );
		} else if (ConstValues.BLANK.equals(dependents.getFuyoSeinengapiMonth())) {
			bindingResult.rejectValue("dependents[" + index + "].fuyoSeinengapiMonth",
					"V00001", new Object[] {"[扶養者" + (index + 1) + "]生年月日（月）"}, null );
		}

		// check day invalid
		if (!ConstValues.BLANK.equals(dependents.getFuyoSeinengapiDay())
				&& checkDayInvalidRange(dependents.getFuyoSeinengapiDay())) {
			bindingResult.rejectValue("dependents[" + index + "].fuyoSeinengapiDay",
					"V00013", new Object[] {"扶養者" + (index + 1), "生年月日（日）"}, null );
		} else if (ConstValues.BLANK.equals(dependents.getFuyoSeinengapiDay())){
			bindingResult.rejectValue("dependents[" + index + "].fuyoSeinengapiDay",
					"V00001", new Object[] {"[扶養者" + (index + 1) + "]生年月日（日）"}, null );
		}

		if (!ConstValues.BLANK.equals(dependents.getFuyoSeinengapiYear())
				&& !ConstValues.BLANK.equals(dependents.getFuyoSeinengapiMonth())
				&& !ConstValues.BLANK.equals(dependents.getFuyoSeinengapiDay())) {

			// check day not exist in future
			if (!isThisDateValid(dependents.getFuyoSeinengapiYear(),
					dependents.getFuyoSeinengapiMonth(),
					dependents.getFuyoSeinengapiDay())) {
				bindingResult.rejectValue("dependents[" + index + "].fuyoSeinengapiDay",
						"V00007", new Object[] {"扶養者" + (index + 1)}, null );
			}
		}
		return bindingResult;

	}

	public static BindingResult checkRelationship(Dependents dependents,
			BindingResult bindingResult, int index) {
		if (!ConstValues.BLANK.equals(dependents.getFuyoZokugara())) {

			// check code fuyoZokugara invalid
			if (checkRelationshipInvalid(dependents.getFuyoZokugara())) {
				bindingResult.rejectValue("dependents[" + index + "].fuyoZokugara",
						"V00013", new Object[] {"扶養者" + (index + 1), "続柄"}, null );
			}

			// check fuyoZokugaraSonota
			if (ConstValues.DEPENDENTS_RELATIONSHIP_07.equals(dependents.getFuyoZokugara()) &&
					ConstValues.BLANK.equals(dependents.getFuyoZokugaraSonota())) {
				bindingResult.rejectValue("dependents[" + index + "].fuyoZokugaraSonota",
						"V00014", new Object[] {"扶養者" + (index + 1)}, null );
			}

			// check fuyoZokugaraSonota
			if (!ConstValues.DEPENDENTS_RELATIONSHIP_07.equals(dependents.getFuyoZokugara()) &&
					!ConstValues.BLANK.equals(dependents.getFuyoZokugaraSonota())) {
				bindingResult.rejectValue("dependents[" + index + "].fuyoZokugaraSonota",
						"V00015", new Object[] {"扶養者" + (index + 1)}, null );
			}

			// check fuyoZokugaraSonota format
			if (ConstValues.DEPENDENTS_RELATIONSHIP_07.equals(dependents.getFuyoZokugara()) &&
					!ConstValues.BLANK.equals(dependents.getFuyoZokugaraSonota()) &&
					!is2ByteCharacters(dependents.getFuyoZokugaraSonota())) {
				bindingResult.rejectValue("dependents[" + index + "].fuyoZokugaraSonota",
						"V00011", new Object[] {"扶養者" + (index + 1), "続柄（その他）"}, null );
			}
		} else {
			bindingResult.rejectValue("dependents[" + index + "].fuyoZokugara",
					"V00001", new Object[] {"[扶養者" + (index + 1) + "]続柄"}, null );
		}
		return bindingResult;
	}

	public static BindingResult checkMyNumber(Dependents dependents,
			BindingResult bindingResult, int index) {
		if (!ConstValues.BLANK.equals(dependents.getFuyoMyNumber())) {
			// check fuyoMyNumber must be 12 characters
			if (dependents.getFuyoMyNumber().length() != 12
					|| !isNumeric(dependents.getFuyoMyNumber())) {
				bindingResult.rejectValue("dependents[" + index + "].fuyoMyNumber",
						"V00010", new Object[] {"扶養者" + (index + 1), "マイナンバー", "12"}, null );
			}

			// check format My number
			if (!is1ByteCharacters(dependents.getFuyoMyNumber())) {
				bindingResult.rejectValue("dependents[" + index + "].fuyoMyNumber",
						"V00012", new Object[] {"扶養者" + (index + 1), "マイナンバー"}, null );
			}
		} else {
			bindingResult.rejectValue("dependents[" + index + "].fuyoMyNumber",
					"V00001", new Object[] {"[扶養者" + (index + 1) + "]マイナンバー"}, null );
		}
		return bindingResult;

	}
	
	public static boolean isValidToken(String string, WebRequest request,
			String requestToken, BindingResult binding, Model model) {
		
		if (!TokenProcessor.isTokenValid(request, requestToken)) {
			binding.rejectValue(string, "S00002", new Object [] {}, null);
			return false;
		}
		TokenProcessor.saveToken(request, model);
		return true;
	}
	
	/** check session is not null
	 *
	 * @param session
	 * @param model
	 * @return boolean
	 */
	public static boolean isNotNullSession(HttpSession session, Model model) {

		// get data form session
		StaffInfoModel staffInfo = (StaffInfoModel)session.getAttribute("staffInfoModel");
		ShainInfoModel shainInfo = (ShainInfoModel)session.getAttribute("shainInfoModel");

		if (staffInfo == null || shainInfo == null) {
			model.addAttribute("errorSession", ConstValues.SESSION_CHECK_VALUE_TRUE);
			return false;
		}
		return true;
	}
}
