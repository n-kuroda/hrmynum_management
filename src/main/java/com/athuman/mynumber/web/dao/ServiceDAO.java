package com.athuman.mynumber.web.dao;

import java.util.List;

import com.athuman.mynumber.web.model.MyNumber;

public interface ServiceDAO {

	/** Query [MyNumber] table by himodukeNo */
	public List<MyNumber> queryMyNumberByHimodukeNo(String himodukeNo);

	public String addMyNumber(MyNumber myNumber);

}
