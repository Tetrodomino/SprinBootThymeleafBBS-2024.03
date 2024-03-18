package com.example.abbs.service;

import java.util.List;

import com.example.abbs.entity.Anniversary;

public interface AnniversaryService {

	List<Anniversary> getAnnivList(String uid, String startDay, String endDay);
	
	List<Anniversary> getAnnivListByDay(String uid, String sdate);
	
	void insertAnniv(Anniversary anniv);
}
