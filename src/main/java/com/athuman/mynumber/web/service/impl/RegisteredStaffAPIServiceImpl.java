package com.athuman.mynumber.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athuman.mynumber.web.dao.TACTServiceDAO;
import com.athuman.mynumber.web.dto.TACTRegisteredStaffResponseDto;
import com.athuman.mynumber.web.model.MyNumber;
import com.athuman.mynumber.web.service.RegisteredStaffAPIService;
import com.athuman.mynumber.web.util.AESUtil;
import com.athuman.mynumber.web.util.StringUtil;

@Service
public class RegisteredStaffAPIServiceImpl implements RegisteredStaffAPIService {

	private TACTServiceDAO tACTServiceDAO;

	@Override
	@Transactional
	public TACTRegisteredStaffResponseDto registeredStaff(String himodukeNo) {
		TACTRegisteredStaffResponseDto dto = new TACTRegisteredStaffResponseDto();

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
				dto.setResult("0");
				return dto;
				
			}

			// in case DB returned no item found
			if (list.size() == 0) {
				dto.setHttpStatus(204);
				dto.setResultMessage("検索結果が0件です。");
				dto.setResult("0");
				
			// in case DB returned item found
			} else if (list.size() == 1) {
				dto.setHttpStatus(200);
				dto.setResultMessage("正常に処理は行われました。");
				dto.setResult("1");
				
			// other errors
			} else {
				dto.setHttpStatus(500);
				dto.setResultMessage("複数件取得されました。");
				dto.setResult("0");
			}
			return dto;
		} else { // invalid parameter

			dto.setHttpStatus(400);
			dto.setResultMessage("リクエストが不正です。");
			dto.setResult("0");

			return dto;
		}
	}

	public void settACTServiceDAO(TACTServiceDAO tACTServiceDAO) {
		this.tACTServiceDAO = tACTServiceDAO;
	}
}
