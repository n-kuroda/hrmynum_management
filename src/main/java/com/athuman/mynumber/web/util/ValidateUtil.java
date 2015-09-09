package com.athuman.mynumber.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.BindingResult;

import com.athuman.mynumber.web.dto.Dependents;
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
	/** validate for form partner
	 *
	 * @param bindingResult
	 * @param dependents
	 * @param staffInfoModel
	 * @param index
	 * @return BindingResult
	 */
	public static BindingResult validFormPartner(BindingResult bindingResult,
			Dependents dependents, StaffInfoModel staffInfoModel, int index) {

		// check form partner has change or form has edit
		if (checkPartnerHasChange(dependents, staffInfoModel) ||checkPartnerFormHasEdit(dependents)) {

			// check DependentsNameSei is required
			if ("".equals(dependents.getDependentsNameSei())) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsNameSei",
						"V00009", new Object[] {"扶養者" + (index + 1)}, null );
			}

			// check DependentsNameSei >= 26 characters
			if (dependents.getDependentsNameSei().length() >= 26) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsNameSei",
						"V00010", new Object[] {"扶養者" + (index + 1), "お名前（姓）", "26"}, null );
			}

			// check format nameSei
			if (!is2ByteCharacters(dependents.getDependentsNameSei())) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsNameSei",
						"V00011", new Object[] {"扶養者" + (index + 1), "お名前（姓）"}, null );
			}

			// check DependentsNameMei >= 26 characters
			if (dependents.getDependentsNameMei().length() >= 26) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsNameMei",
						"V00010", new Object[] {"扶養者" + (index + 1), "お名前（名）", "26"}, null );
			}

			// check format nameMei
			if (!is2ByteCharacters(dependents.getDependentsNameMei())) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsNameMei",
						"V00011", new Object[] {"扶養者" + (index + 1), "お名前（名）"}, null );
			}

			// check year invalid
			if (checkYearInvalidRange(dependents.getDependentsBirthdayYear())) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsBirthdayYear",
						"V00013", new Object[] {"扶養者" + (index + 1), "生年月日（年）"}, null );
			}

			// check month invalid
			if (checkMonthInvalidRange(dependents.getDependentsBirthdayMonth())) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsBirthdayMonth",
						"V00013", new Object[] {"扶養者" + (index + 1), "生年月日（月）"}, null );
			}

			// check day invalid
			if (checkDayInvalidRange(dependents.getDependentsBirthdayDay())) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsBirthdayDay",
						"V00013", new Object[] {"扶養者" + (index + 1), "生年月日（日）"}, null );
			}

			// check day not exist in future
			if (!isThisDateValid(dependents.getDependentsBirthdayYear(),
					dependents.getDependentsBirthdayMonth(),
					dependents.getDependentsBirthdayDay())) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsBirthdayDay",
						"V00007", new Object[] {"扶養者" + (index + 1)}, null );
			}

			// check code DependentsRelationship invalid
			if (checkRelationshipInvalid(dependents.getDependentsRelationship())) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsRelationship",
						"V00013", new Object[] {"扶養者" + (index + 1), "続柄"}, null );
			}

			// check DependentsRelationshipOther
			if (ConstValues.DEPENDENTS_RELATIONSHIP_07.equals(dependents.getDependentsRelationship()) &&
					ConstValues.BLANK.equals(dependents.getDependentsRelationshipOther())) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsRelationshipOther",
						"V00014", new Object[] {"扶養者" + (index + 1)}, null );
			}

			// check DependentsRelationshipOther
			if (!ConstValues.DEPENDENTS_RELATIONSHIP_07.equals(dependents.getDependentsRelationship()) &&
					!ConstValues.BLANK.equals(dependents.getDependentsRelationshipOther())) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsRelationshipOther",
						"V00015", new Object[] {"扶養者" + (index + 1)}, null );
			}

			// check DependentsRelationshipOther format
			if (ConstValues.DEPENDENTS_RELATIONSHIP_07.equals(dependents.getDependentsRelationship()) &&
					!ConstValues.BLANK.equals(dependents.getDependentsRelationshipOther()) &&
					!is2ByteCharacters(dependents.getDependentsRelationshipOther())) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsRelationshipOther",
						"V00011", new Object[] {"扶養者" + (index + 1), "続柄（その他）"}, null );
			}

			// check DependentsMyNumber must be 12 characters
			if (dependents.getDependentsMyNumber().length() != 12) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsMyNumber",
						"V00010", new Object[] {"扶養者" + (index + 1), "マイナンバー", "12"}, null );
			}

			// check format My number
			if (checkFormatField("^[0-9]*$",dependents.getDependentsMyNumber())) {
				bindingResult.rejectValue("dependents[" + index + "].dependentsMyNumber",
						"V00012", new Object[] {"扶養者" + (index + 1), "マイナンバー"}, null );
			}
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
		if (yearToCheck >= 1990 && yearToCheck<= curYear) {
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
		if (monthToCheck > 0 && monthToCheck<= 12) {
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
		if (dayToCheck > 0 && dayToCheck <= 31) {
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

		String[] stringRelation = {ConstValues.DEPENDENTS_RELATIONSHIP_01,ConstValues.DEPENDENTS_RELATIONSHIP_02,
				ConstValues.DEPENDENTS_RELATIONSHIP_03,ConstValues.DEPENDENTS_RELATIONSHIP_04,
				ConstValues.DEPENDENTS_RELATIONSHIP_05,ConstValues.DEPENDENTS_RELATIONSHIP_06,
				ConstValues.DEPENDENTS_RELATIONSHIP_07};
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

	/** check field with pattern
	 *
	 * @param patternString
	 * @param checkString
	 * @return boolean
	 */
	public static boolean checkFormatField(String patternString, String checkString){
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(checkString);
		if (matcher.find()) {
			return false;
		} else {
			return true;
		}
	}

	/** check staff name has change
	 *
	 * @param dependents
	 * @param staffInfoModel
	 * @return boolean
	 */
	public static boolean checkPartnerHasChange(Dependents dependents, StaffInfoModel staffInfoModel){
		if (!dependents.getDependentsNameSei().equals(staffInfoModel.getStaffNameSei())) {
			return true;
		}
		return false;
	}

	/** check form has edit
	 *
	 * @param dependents
	 * @return boolean
	 */
	public static boolean checkPartnerFormHasEdit(Dependents dependents){

		if (!ConstValues.BLANK.equals(dependents.getDependentsNameMei())) {
			return true;
		}

		if (!ConstValues.BLANK.equals(dependents.getDependentsBirthdayYear())) {
			return true;
		}

		if (!ConstValues.BLANK.equals(dependents.getDependentsBirthdayMonth())) {
			return true;
		}

		if (!ConstValues.BLANK.equals(dependents.getDependentsBirthdayDay())) {
			return true;
		}

		if (!ConstValues.BLANK.equals(dependents.getDependentsRelationship())) {
			return true;
		}

		if (ConstValues.DEPENDENTS_RELATIONSHIP_07.equals(dependents.getDependentsRelationship()) &&
				ConstValues.BLANK.equals(dependents.getDependentsRelationshipOther())) {
			return true;
		}

		if (!ConstValues.DEPENDENTS_RELATIONSHIP_07.equals(dependents.getDependentsRelationship()) &&
				!ConstValues.BLANK.equals(dependents.getDependentsRelationshipOther())) {
			return true;
		}

		if (!ConstValues.BLANK.equals(dependents.getDependentsMyNumber())) {
			return true;
		}

		if (ConstValues.CHECKBOX_SELECT.equals(dependents.getNo3Insured())) {
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
}
