package com.athuman.mynumber.web.model;

import java.io.Serializable;
import java.util.List;

import com.athuman.mynumber.web.dto.Dependents;

public class DependentsInfoListModel implements Serializable {

	private static final long serialVersionUID = 4075518567454877490L;
	private List<Dependents> dependents;

	public List<Dependents> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependents> dependents) {
		this.dependents = dependents;
	}

}
