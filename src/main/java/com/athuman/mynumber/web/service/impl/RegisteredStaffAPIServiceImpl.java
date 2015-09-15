package com.athuman.mynumber.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athuman.mynumber.web.dao.ServiceDAO;
import com.athuman.mynumber.web.dto.RegisteredStaffAPIResponseDto;
import com.athuman.mynumber.web.model.MyNumber;
import com.athuman.mynumber.web.service.RegisteredStaffAPIService;
import com.athuman.mynumber.web.util.AESUtil;
import com.athuman.mynumber.web.util.ConstValues;
import com.athuman.mynumber.web.util.StringUtil;

@Service
public class RegisteredStaffAPIServiceImpl implements RegisteredStaffAPIService {

	private ServiceDAO serviceDAO;

	@Override
	@Transactional
	public RegisteredStaffAPIResponseDto registeredStaff(String himodukeNo) {
		RegisteredStaffAPIResponseDto dto = new RegisteredStaffAPIResponseDto();

		// check [himodukeNo] is valid or not
		if (StringUtil.isNotEmpty(himodukeNo) &&
				himodukeNo.length() == ConstValues.HIMODUKENO_LENGTH &&
				StringUtil.isValid(himodukeNo)) {

			// search [himodukeNo] in [MyNumber] table
			List<MyNumber> list = new ArrayList<MyNumber>();
			try {
				list = serviceDAO.queryMyNumberByHimodukeNo(AESUtil.encrypt(himodukeNo));
			} catch (Exception e) {

				// return status 500 in case DB error happens
				dto.setHttpStatus(ConstValues.API_STATUS_500);
				dto.setResultMessage(ConstValues.API_MSG_UNEXPECTED_ERROR);
				dto.setResult(ConstValues.API_RESULT_0);
				return dto;

			}

			// in case DB returned no item found
			if (ConstValues.API_RETURNED_LIST_LENGTH_0 == list.size()) {
				dto.setHttpStatus(ConstValues.API_STATUS_204);
				dto.setResultMessage(ConstValues.API_MSG_NOITEM_RETURNED);
				dto.setResult(ConstValues.API_RESULT_0);
				
			// in case DB returned item found
			} else if (ConstValues.API_RETURNED_LIST_LENGTH_1 == list.size()) {
				dto.setHttpStatus(ConstValues.API_STATUS_200);
				dto.setResultMessage(ConstValues.API_MSG_OK);
				dto.setResult(ConstValues.API_RESULT_1);
				
			// other errors
			} else {
				dto.setHttpStatus(ConstValues.API_STATUS_500);
				dto.setResultMessage(ConstValues.API_OTHER_ERROR);
				dto.setResult(ConstValues.API_RESULT_0);
			}
			return dto;
		} else { // invalid parameter

			dto.setHttpStatus(ConstValues.API_STATUS_400);
			dto.setResultMessage(ConstValues.API_MSG_INVALID);
			dto.setResult(ConstValues.API_RESULT_0);

			return dto;
		}
	}

	public void setServiceDAO(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}
}
