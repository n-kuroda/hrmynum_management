package com.athuman.mynumber.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athuman.mynumber.web.dao.TACTServiceDAO;
import com.athuman.mynumber.web.dto.TACTMyNumberResponseDto;
import com.athuman.mynumber.web.model.MyNumber;
import com.athuman.mynumber.web.service.MyNumberAPIService;
import com.athuman.mynumber.web.util.StringUtil;

@Service
public class MyNumberAPIServiceImpl implements MyNumberAPIService {

	private TACTServiceDAO tACTServiceDAO;
	
	@Override
	@Transactional
	public TACTMyNumberResponseDto myNumber(String himodukeNo) {

		TACTMyNumberResponseDto dto = new TACTMyNumberResponseDto();
		// check [himodukeNo] is valid or not
		if (StringUtil.isNotEmpty(himodukeNo) && himodukeNo.length() == 12 &&
				StringUtil.isValid(himodukeNo)) {

			// search [himodukeNo] in [MyNumber] table
			List<MyNumber> list = tACTServiceDAO.queryMyNumberByHimodukeNo(himodukeNo);

			if (list.size() == 0) {
				dto.setHttpStatus(204);
				dto.setResultMessage("検索結果が0件です。");
				dto.setMyNumber("");
			} else if (list.size() == 1) {
				dto.setHttpStatus(200);
				dto.setResultMessage("正常に処理は行われました。");
				dto.setMyNumber(list.get(0).getStaffMyNumber());
			} else {
				dto.setHttpStatus(400);
				dto.setResultMessage("予期せぬエラー。複数件取得されました。");
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
	
	public void settACTServiceDAO(TACTServiceDAO tACTServiceDAO) {
		this.tACTServiceDAO = tACTServiceDAO;
	}

}
