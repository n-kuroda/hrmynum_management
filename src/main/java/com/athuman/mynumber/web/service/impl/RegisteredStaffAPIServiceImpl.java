package com.athuman.mynumber.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athuman.mynumber.web.dao.ServiceDAO;
import com.athuman.mynumber.web.dto.RegisteredStaffAPIResponseDto;
import com.athuman.mynumber.web.model.MyNumber;
import com.athuman.mynumber.web.service.RegisteredStaffAPIService;
import com.athuman.mynumber.web.util.ConstValues;
import com.athuman.mynumber.web.util.StringUtil;

@Service
public class RegisteredStaffAPIServiceImpl implements RegisteredStaffAPIService {

	private ServiceDAO serviceDAO;

	@Override
	@Transactional
	public ResponseEntity<RegisteredStaffAPIResponseDto> registeredStaff(String himodukeNo) {

		RegisteredStaffAPIResponseDto dto = new RegisteredStaffAPIResponseDto();

		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		
		// check [himodukeNo] is valid or not
		if (StringUtil.isNotEmpty(himodukeNo) &&
				himodukeNo.length() == ConstValues.HIMODUKENO_LENGTH &&
				StringUtil.isValid(himodukeNo)) {

			// search [himodukeNo] in [MyNumber] table
			List<MyNumber> list = new ArrayList<MyNumber>();
			try {
				list = serviceDAO.queryMyNumberByHimodukeNo(himodukeNo);
			} catch (Exception e) {

				// return status 500 in case DB error happens
				dto.setResultMessage(ConstValues.API_MSG_UNEXPECTED_ERROR);
				dto.setResult(ConstValues.API_RESULT_0);				
				status = HttpStatus.INTERNAL_SERVER_ERROR;

				return new ResponseEntity<RegisteredStaffAPIResponseDto>(dto, headers , status);
			}

			// in case DB returned no item found
			if (ConstValues.API_RETURNED_LIST_LENGTH_0 == list.size()) {
				status = HttpStatus.OK;
				dto.setResultMessage(ConstValues.API_MSG_NOITEM_RETURNED);
				dto.setResult(ConstValues.API_RESULT_0);
				
			// in case DB returned item found
			} else if (ConstValues.API_RETURNED_LIST_LENGTH_1 == list.size()) {
				status = HttpStatus.OK;
				dto.setResultMessage(ConstValues.API_MSG_OK);
				dto.setResult(ConstValues.API_RESULT_1);
				
			// other errors
			} else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				dto.setResultMessage(ConstValues.API_OTHER_ERROR);
				dto.setResult(ConstValues.API_RESULT_0);
			}
		} else { // invalid parameter

			status = HttpStatus.BAD_REQUEST;
			dto.setResultMessage(ConstValues.API_MSG_INVALID);
			dto.setResult(ConstValues.API_RESULT_0);

		}
		
		return new ResponseEntity<RegisteredStaffAPIResponseDto>(dto, headers, status);
	}

	public void setServiceDAO(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}
}
