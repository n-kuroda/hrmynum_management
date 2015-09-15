package com.athuman.mynumber.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athuman.mynumber.web.dao.ServiceDAO;
import com.athuman.mynumber.web.dto.MyNumberResponseDto;
import com.athuman.mynumber.web.dto.RegistConfirmDto;
import com.athuman.mynumber.web.model.MyNumber;
import com.athuman.mynumber.web.service.MyNumberAPIService;
import com.athuman.mynumber.web.util.AESUtil;
import com.athuman.mynumber.web.util.ConstValues;
import com.athuman.mynumber.web.util.StringUtil;

@Service
public class MyNumberAPIServiceImpl implements MyNumberAPIService {

	private ServiceDAO serviceDAO;

	@Override
	@Transactional
	public MyNumberResponseDto myNumber(String himodukeNo) {

		MyNumberResponseDto dto = new MyNumberResponseDto();

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
				dto.setMyNumber("");
				return dto;
			}

			// in case DB returned no item found
			if (ConstValues.API_RETURNED_LIST_LENGTH_0 == list.size()) {
				dto.setHttpStatus(ConstValues.API_STATUS_204);
				dto.setResultMessage(ConstValues.API_MSG_NOITEM_RETURNED);
				dto.setMyNumber("");

			// in case DB returned item found
			} else if (ConstValues.API_RETURNED_LIST_LENGTH_1 == list.size()) {
				dto.setHttpStatus(ConstValues.API_STATUS_200);
				dto.setResultMessage(ConstValues.API_MSG_OK);
				try {
					dto.setMyNumber(AESUtil.decrypt(list.get(0).getStaffMyNumber()));
				} catch (Exception e) {
					e.printStackTrace();
				}

			// other errors
			} else {
				dto.setHttpStatus(ConstValues.API_STATUS_500);
				dto.setResultMessage(ConstValues.API_OTHER_ERROR);
				dto.setMyNumber("");
			}

			return dto;
		} else { // invalid parameter

			dto.setHttpStatus(ConstValues.API_STATUS_400);
			dto.setResultMessage(ConstValues.API_MSG_INVALID);
			dto.setMyNumber("");

			return dto;
		}
	}

	@Override
	@Transactional
	public String registMyNumber(MyNumber myNumber) {
		return serviceDAO.addMyNumber(myNumber);
	}
	
	@Override
	public MyNumberResponseDto collectionInfo(RegistConfirmDto registConfirmDto) {

		MyNumberResponseDto responseDto = new MyNumberResponseDto();
		responseDto.setHttpStatus(ConstValues.API_STATUS_204);
		responseDto.setMyNumber("");
		responseDto.setResultMessage("");

		return responseDto;
	}
	
	public void setServiceDAO(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}

}
