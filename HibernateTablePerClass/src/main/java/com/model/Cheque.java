package com.model;

import java.util.Date;

public class Cheque extends Payment {
	private Date ch_date;

	public Date getCh_date() {
		return ch_date;
	}

	public void setCh_date(Date ch_date) {
		this.ch_date = ch_date;
	}

}
