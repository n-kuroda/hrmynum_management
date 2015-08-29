package com.athuman.mynumber.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.athuman.mynumber.web.dao.TACTServiceDAO;
import com.athuman.mynumber.web.dto.TACTMyNumberResponseDto;
import com.athuman.mynumber.web.dto.TACTRegisteredStaffResponseDto;
import com.athuman.mynumber.web.model.MyNumber;
import com.athuman.mynumber.web.service.TACTService;

@Service
public class TACTServiceImpl implements TACTService {
	
	private TACTServiceDAO tACTServiceDAO;

	@Override
	@Transactional
	public TACTRegisteredStaffResponseDto registeredStaff(String himodukeNo) {
		TACTRegisteredStaffResponseDto dto = new TACTRegisteredStaffResponseDto();
		
		// check [himodukeNo] is valid or not
		
		
		// search [himodukeNo] in [MyNumber] table
		List<MyNumber> list = tACTServiceDAO.queryMyNumberByHimodukeNo(himodukeNo);
		
		if (list.size() == 0) {
			dto.setHttpStatus(204);
			dto.setResultMessage("検索結果が0件です。");
			dto.setResult("0");
		} else if (list.size() == 1) {
			dto.setHttpStatus(200);
			dto.setResultMessage("正常に処理は行われました。");
			dto.setResult("1");
		} else {
			dto.setHttpStatus(400);
			dto.setResultMessage("予期せぬエラー。複数件取得されました。");
			dto.setResult("0");
		}
		return dto;
		
	}

	@Override
	@Transactional
	public TACTMyNumberResponseDto myNumber(String himodukeNo) {
		TACTMyNumberResponseDto dto = new TACTMyNumberResponseDto();
		// check [himodukeNo] is valid or not
		
		
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
	}

	public void settACTServiceDAO(TACTServiceDAO tACTServiceDAO) {
		this.tACTServiceDAO = tACTServiceDAO;
	}
	
}
