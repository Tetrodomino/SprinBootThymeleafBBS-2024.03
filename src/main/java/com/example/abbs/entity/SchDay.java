package com.example.abbs.entity;

import java.util.List;

public class SchDay {

	private int day;	// 일(1 ~ 31)
	private int date;	// 요일(0 일요일 ~ 6 토요일)
	private int isHoliday;	// 공휴일 여부
	private int isOtherMonth;	// 다른 달의 표시 여부
	private String sdate;	// String 형식으로 나타낸 년월일 ex: 20240313
	private List<String> annivList; // 기념일 목록
	private List<Schedule> schedulList; // 일정 목록
	
	public SchDay() {
	}

	public SchDay(int day, int date, int isHoliday, int isOtherMonth, String sdate, List<String> annivList,
			List<Schedule> schedulList) {
		this.day = day;
		this.date = date;
		this.isHoliday = isHoliday;
		this.isOtherMonth = isOtherMonth;
		this.sdate = sdate;
		this.annivList = annivList;
		this.schedulList = schedulList;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getIsHoliday() {
		return isHoliday;
	}

	public void setIsHoliday(int isHoliday) {
		this.isHoliday = isHoliday;
	}

	public int getIsOtherMonth() {
		return isOtherMonth;
	}

	public void setIsOtherMonth(int isOtherMonth) {
		this.isOtherMonth = isOtherMonth;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public List<String> getAnnivList() {
		return annivList;
	}

	public void setAnnivList(List<String> annivList) {
		this.annivList = annivList;
	}

	public List<Schedule> getSchedulList() {
		return schedulList;
	}

	public void setSchedulList(List<Schedule> schedulList) {
		this.schedulList = schedulList;
	}

	@Override
	public String toString() {
		return "SchDay [day=" + day + ", date=" + date + ", isHoliday=" + isHoliday + ", isOtherMonth=" + isOtherMonth
				+ ", sdate=" + sdate + ", annivList=" + annivList + ", schedulList=" + schedulList + "]";
	}
}
