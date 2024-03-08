package com.example.abbs.entity;

import java.time.LocalDateTime;

public class Like {

	private int lid;
	private String uid;
	private int bid;
	private int value;
	private LocalDateTime clickTime;
	
	public Like() {
	}

	public Like(String uid, int bid) {
		this.uid = uid;
		this.bid = bid;
	}

	public Like(int lid, String uid, int bid, int value) {
		this.lid = lid;
		this.uid = uid;
		this.bid = bid;
		this.value = value;
	}

	public Like(int lid, String uid, int bid, int value, LocalDateTime clickTime) {
		this.lid = lid;
		this.uid = uid;
		this.bid = bid;
		this.value = value;
		this.clickTime = clickTime;
	}

	@Override
	public String toString() {
		return "Like [lid=" + lid + ", uid=" + uid + ", bid=" + bid + ", value=" + value + ", clickTime=" + clickTime
				+ "]";
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public LocalDateTime getClickTime() {
		return clickTime;
	}

	public void setClickTime(LocalDateTime clickTime) {
		this.clickTime = clickTime;
	}
	
}
