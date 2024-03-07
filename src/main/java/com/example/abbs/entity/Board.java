package com.example.abbs.entity;

import java.time.LocalDateTime;

public class Board {

	private int bid;
	private String title;
	private String content;
	private String uid;
	private LocalDateTime writeTime;
	private int isDeleted;
	private int viewCount;
	private int replyCount;
	private int likeCount;
	private String files;
	
	public Board(String title, String content, String uid) {
		this.title = title;
		this.content = content;
		this.uid = uid;
	}

	public Board() {
	}

	public Board(String title, String content, String uid, String files) {
		this.title = title;
		this.content = content;
		this.uid = uid;
		this.files = files;
	}

	public Board(int bid, String title, String content, String uid, LocalDateTime writeTime, int isDeleted,
			int viewCount, int replyCount, int likeCount, String files) {
		this.bid = bid;
		this.title = title;
		this.content = content;
		this.uid = uid;
		this.writeTime = writeTime;
		this.isDeleted = isDeleted;
		this.viewCount = viewCount;
		this.replyCount = replyCount;
		this.likeCount = likeCount;
		this.files = files;
	}

	@Override
	public String toString() {
		return "Board [bid=" + bid + ", title=" + title + ", content=" + content + ", uid=" + uid + ", writeTime="
				+ writeTime + ", isDeleted=" + isDeleted + ", viewCount=" + viewCount + ", replyCount=" + replyCount
				+ ", likeCount=" + likeCount + ", files=" + files + "]";
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public LocalDateTime getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(LocalDateTime writeTime) {
		this.writeTime = writeTime;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
}
