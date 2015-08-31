package com.athuman.mynumber.web.util;

import org.springframework.validation.BindingResult;

public class ValidateUtil {

	/* check input valid */
	public static BindingResult checkInputValid(String itemName,
			String itemValue, BindingResult bindingResult, int digit) {

		/* check not null */
		if (itemValue.trim().equals("")) {
			bindingResult.rejectValue(itemName, "V00001",
					new Object[] { itemName }, null);
		} else {
			/* check is number */
			if (!itemValue.matches("^[-+]?\\d+(\\.\\d+)?$")) {
				bindingResult.rejectValue(itemName, "V00003",
						new Object[] { itemName }, null);
			}
		}
		/* check size = 9 */
		if (itemValue.length() != digit) {
			bindingResult.rejectValue(itemName, "V00002", new Object[] {
					itemName, digit }, null);
		}
		return bindingResult;
	}
}
