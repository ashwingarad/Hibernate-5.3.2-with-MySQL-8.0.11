package com.model;

import java.util.Calendar;

public class Licence {
	private String licence_no;
	private String name;
	private Calendar validity;

	public String getLicence_no() {
		return licence_no;
	}

	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getValidity() {
		return validity;
	}

	public void setValidity(Calendar validity) {
		this.validity = validity;
	}
}
