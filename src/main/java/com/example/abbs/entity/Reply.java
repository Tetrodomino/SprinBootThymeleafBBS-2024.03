package com.example.abbs.entity;

import java.time.LocalDateTime;

public class Reply {

	private int rid;
	private int bid;
	private String uid;
	private String content;
	private LocalDateTime replyTime;
	private int isMine;
	private String uname;
	
	public Reply() {
	}

	public Reply(String content, String uid, int bid) {
		this.content = content;
		this.uid = uid;
		this.bid = bid;
	}

	public Reply(String content, String uid, int bid, int isMine) {
		this.content = content;
		this.uid = uid;
		this.bid = bid;
		this.isMine = isMine;
	}

	public Reply(int rid, String content, LocalDateTime replyTime, String uid, int bid, int isMine) {
		this.rid = rid;
		this.content = content;
		this.replyTime = replyTime;
		this.uid = uid;
		this.bid = bid;
		this.isMine = isMine;
	}

	public Reply(int rid, String content, LocalDateTime replyTime, String uid, int bid, int isMine, String uname) {
		this.rid = rid;
		this.content = content;
		this.replyTime = replyTime;
		this.uid = uid;
		this.bid = bid;
		this.isMine = isMine;
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "Reply [rid=" + rid + ", content=" + content + ", replyTime=" + replyTime + ", uid=" + uid + ", bid=" + bid
				+ ", isMine=" + isMine + "]";
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(LocalDateTime replyTime) {
		this.replyTime = replyTime;
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

	public int getIsMine() {
		return isMine;
	}

	public void setIsMine(int isMine) {
		this.isMine = isMine;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
}
