package com.athuman.mynumber.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athuman.mynumber.web.dao.TACTServiceDAO;
import com.athuman.mynumber.web.dto.TACTMyNumberResponseDto;
import com.athuman.mynumber.web.dto.TACTRegistConfirmDto;
import com.athuman.mynumber.web.model.MyNumber;
import com.athuman.mynumber.web.service.MyNumberAPIService;
import com.athuman.mynumber.web.util.AESUtil;
import com.athuman.mynumber.web.util.StringUtil;

@Service
public class MyNumberAPIServiceImpl implements MyNumberAPIService {

	private TACTServiceDAO tACTServiceDAO;
	
	@Override
	@Transactional
	public TACTMyNumberResponseDto myNumber(String himodukeNo) {

		TACTMyNumberResponseDto dto = new TACTMyNumberResponseDto();

		// check [himodukeNo] is valid or not
		if (StringUtil.isNotEmpty(himodukeNo) && himodukeNo.length() == 36 &&
				StringUtil.isValid(himodukeNo)) {

			// search [himodukeNo] in [MyNumber] table
			List<MyNumber> list = new ArrayList<MyNumber>();
			try {
				list = tACTServiceDAO.queryMyNumberByHimodukeNo(AESUtil.encrypt(himodukeNo));
			} catch (Exception e) {
				
				// return status 500 in case DB error happens
				dto.setHttpStatus(500);
				dto.setResultMessage("予期せぬエラーが発生しました。");
				dto.setMyNumber("");
				return dto;
			}

			// in case DB returned no item found
			if (list.size() == 0) {
				dto.setHttpStatus(204);
				dto.setResultMessage("検索結果が0件です。");
				dto.setMyNumber("");
				
			// in case DB returned item found
			} else if (list.size() == 1) {
				dto.setHttpStatus(200);
				dto.setResultMessage("正常に処理は行われました。");
				try {
					dto.setMyNumber(AESUtil.decrypt(list.get(0).getStaffMyNumber()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			// other errors
			} else {
				dto.setHttpStatus(500);
				dto.setResultMessage("複数件取得されました。");
				dto.setMyNumber("");
			}

			return dto;
		} else { // invalid parameter

			dto.setHttpStatus(400);
			dto.setResultMessage("リクエストが不正です。");
			dto.setMyNumber("");

			return dto;
		}
	}
	
	@Override
	@Transactional
	public String registMyNumber(MyNumber myNumber) {
		return tACTServiceDAO.addMyNumber(myNumber);
	}
	
	@Override
	public TACTMyNumberResponseDto collectionInfo(TACTRegistConfirmDto tACTRegistConfirmDto) {
		
		TACTMyNumberResponseDto responseDto = new TACTMyNumberResponseDto();
		responseDto.setHttpStatus(204);
		responseDto.setMyNumber("");
		responseDto.setResultMessage("");
		
		return responseDto;
	}
	
	public void settACTServiceDAO(TACTServiceDAO tACTServiceDAO) {
		this.tACTServiceDAO = tACTServiceDAO;
	}

}
